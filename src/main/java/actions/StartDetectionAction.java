package actions;

import beans.PsiClassBean;
import com.google.gson.Gson;
import com.intellij.ide.highlighter.JavaFileType;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiJavaFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import org.jetbrains.annotations.NotNull;
import testSmellInfo.classSmellInfo.ClassWithEagerTest;
import testSmellInfo.classSmellInfo.ClassWithGeneralFixture;
import testSmellInfo.classSmellInfo.ClassWithLackOfCohesion;
import testSmellInfo.methodSmellInfo.MethodWithEagerTest;
import testSmellInfo.methodSmellInfo.MethodWithGeneralFixture;
import utilities.BeanGenerator;
import utilities.ClassDetector;
import utilities.HostConnection;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class StartDetectionAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        List<VirtualFile> virtualFiles =
            new ArrayList<>(FileTypeIndex.getFiles(JavaFileType.INSTANCE,
                    GlobalSearchScope.projectScope(Objects.requireNonNull(e.getProject()))));

        PsiManager psiManager = PsiManager.getInstance(e.getProject());
        List<PsiJavaFile> psiJavaFiles = new ArrayList<>();
        for (VirtualFile vf: virtualFiles) {
            try {
                psiJavaFiles.add((PsiJavaFile) psiManager.findFile(vf));
            } catch (ClassCastException exception) {
                exception.printStackTrace();
            }
        }

        List<PsiClass> classes = new ArrayList<>();
        for (PsiJavaFile psiJavaFile: psiJavaFiles) {
            classes.addAll(Arrays.asList(psiJavaFile.getClasses()));
        }

        List<PsiClassBean> testClasses = new ArrayList<>();
        for (PsiClass psiClass: classes) {
            if (ClassDetector.isTestClass(psiClass)) {
                PsiClass productionClass = ClassDetector.findProductionClass(psiClass, classes);
                if (productionClass != null) {
                    testClasses.add(BeanGenerator.generateClassBean(
                            e.getProject().getName(),
                            psiClass,
                            productionClass));
                }
            }
        }

        List<ClassWithEagerTest> classesWithEagerTest = new ArrayList<>();
        List<ClassWithGeneralFixture> classesWithGeneralFixture = new ArrayList<>();
        List<ClassWithLackOfCohesion> classesWithLackOfCohesion = new ArrayList<>();
        for (PsiClassBean testClass: testClasses) {
            try {
                // Clean DB
                HostConnection.cleanHostDB();

                // Sending class to host
                Gson gson = new Gson();
                HostConnection.sendClassToHost(gson.toJson(testClass));

                // Check for eager test
                List<PsiClassBean> eagerTest = HostConnection.getClassesWithEagerTest();
                if (!eagerTest.isEmpty()) {
                    classesWithEagerTest.add(
                            new ClassWithEagerTest(eagerTest.get(0),
                                    HostConnection.getMethodsWithEagerTest())
                    );
                }

                // Check for general fixture
                List<PsiClassBean> generalFixture = HostConnection.getClassesWithGeneralFixture();
                if (!generalFixture.isEmpty()) {
                    classesWithGeneralFixture.add(
                            new ClassWithGeneralFixture(generalFixture.get(0),
                                    HostConnection.getMethodsWithGeneralFixture())
                    );
                }

                // Check for lack of cohesion
                List<PsiClassBean> lackOfCohesion = HostConnection.getClassesWithLackOfCohesion();
                if (!lackOfCohesion.isEmpty()) {
                    classesWithLackOfCohesion.add(
                            new ClassWithLackOfCohesion(lackOfCohesion.get(0))
                    );
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

        // Showing detection results
        if (classesWithEagerTest.isEmpty() &&
                classesWithGeneralFixture.isEmpty() &&
                classesWithLackOfCohesion.isEmpty()) {
            Messages.showMessageDialog(
                    "Nothing smelly detected. Go on.",
                    "Detection Info",
                    Messages.getInformationIcon()
            );
        } else {
            String result = "";

            // Appending eager test detection results
            if (!classesWithEagerTest.isEmpty()) {
                result += "Eager Test detected in class(es):" + "\n";
                for (ClassWithEagerTest classWithEagerTest: classesWithEagerTest) {
                    result += "- " +
                            classWithEagerTest.getClassWithEagerTest().getClassName() +
                            ", caused by method(s): ";
                    for (MethodWithEagerTest methodWithEagerTest:
                            classWithEagerTest.getMethodsWithEagerTest()) {
                        result += methodWithEagerTest.getMethodWithEagerTest().getMethodName() +
                                "(), ";
                    }
                    result = result.substring(0, result.length()-2) + ";" + "\n";
                }
                result = result.substring(0, result.length()-2) + "." + "\n\n";
            }

            // Appending general fixture detection results
            if (!classesWithGeneralFixture.isEmpty()) {
                result += "General Fixture detected in class(es):" + "\n";
                for (ClassWithGeneralFixture classWithGeneralFixture: classesWithGeneralFixture) {
                    result += "- " +
                            classWithGeneralFixture.getClassWithGeneralFixture().getClassName() +
                            ", caused by method(s): ";
                    for (MethodWithGeneralFixture methodWithGeneralFixture:
                            classWithGeneralFixture.getMethodsWithGeneralFixture()) {
                        result += methodWithGeneralFixture.getMethodWithGeneralFixture().getMethodName() +
                                "(), ";
                    }
                    result = result.substring(0, result.length()-2) + ";" + "\n";
                }
                result = result.substring(0, result.length()-2) + "." + "\n\n";
            }

            // Appending lack of cohesion detection results
            if (!classesWithLackOfCohesion.isEmpty()) {
                result += "Lack of Cohesion detected in class(es):" + "\n";
                for (ClassWithLackOfCohesion classWithLackOfCohesion: classesWithLackOfCohesion) {
                    result += "- " +
                            classWithLackOfCohesion.getClassWithLackOfCohesion().getClassName() +
                            ";" + "\n";
                }
                result = result.substring(0, result.length()-2) + "." + "\n";
            }

            // Printing some data about the detection
            int numberOfMehodsCausingEagerTest = 0;
            for (ClassWithEagerTest classWithEagerTest: classesWithEagerTest) {
                numberOfMehodsCausingEagerTest += classWithEagerTest
                        .getMethodsWithEagerTest().size();
            }

            int numberOfMehodsCausingGeneralFixture = 0;
            for (ClassWithGeneralFixture classWithGeneralFixture: classesWithGeneralFixture) {
                numberOfMehodsCausingGeneralFixture += classWithGeneralFixture
                        .getMethodsWithGeneralFixture().size();
            }

            System.out.println("Numer of test classes: " + testClasses.size());
            System.out.println("Number of classes with Eager Test: " + classesWithEagerTest.size());
            System.out.println("Number of methods causing Eager Test: " + numberOfMehodsCausingEagerTest);
            System.out.println("Number of classes with General Fixture: " +
                    classesWithGeneralFixture.size());
            System.out.println("Number of methods causing General Fixture: " +
                    numberOfMehodsCausingGeneralFixture);
            System.out.println("Number of classes with Lack of Cohesion: " +
                    classesWithLackOfCohesion.size());
            System.out.println("\n" + result);

            // Printing detection results
            Messages.showMessageDialog(
                    result,
                    "Detection Info",
                    Messages.getWarningIcon()
            );
        }
    }
}
