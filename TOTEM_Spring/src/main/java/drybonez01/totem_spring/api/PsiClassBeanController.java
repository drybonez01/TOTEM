package drybonez01.totem_spring.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import drybonez01.totem_spring.model.PsiClassBean;
import drybonez01.totem_spring.model.detectionTools.structuralRules.LackOfCohesionOfTestSmellStructural;
import drybonez01.totem_spring.model.detectionTools.textualRules.EagerTestTextual;
import drybonez01.totem_spring.model.detectionTools.textualRules.GeneralFixtureTextual;
import drybonez01.totem_spring.model.testSmellInfo.ProjectInfo;
import drybonez01.totem_spring.model.testSmellInfo.methodSmellInfo.MethodWithEagerTest;
import drybonez01.totem_spring.model.testSmellInfo.methodSmellInfo.MethodWithGeneralFixture;
import drybonez01.totem_spring.service.PsiClassBeanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("api/v1/psi")
@RestController
public class PsiClassBeanController {
    private final PsiClassBeanService psiClassBeanService;
    private final String resultsDirectory = "./TOTEM_Spring/results/";

    @Autowired
    public PsiClassBeanController(PsiClassBeanService psiClassBeanService) {
        this.psiClassBeanService = psiClassBeanService;
    }

    @PostMapping
    public void addPsiClassBean(@RequestBody PsiClassBean psiClassBean) {
        psiClassBeanService.addPsiClassBean(psiClassBean);

        // Creates the directory, if it doesn't exist
        try {
            Path of = Path.of(resultsDirectory);
            if (!Files.exists(of)) {
                Files.createDirectory(of);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Checks if the file exists, or creates a new one
        String path = resultsDirectory + psiClassBean.getProjectName() + ".json";
        if (Files.exists(Paths.get(path))) {
            try {
                // Reads old file
                Reader reader = new FileReader(path);
                Gson gsonReader = new Gson();
                ProjectInfo projectInfo = gsonReader.fromJson(reader, ProjectInfo.class);
                projectInfo.setNumberOfTestClasses(projectInfo.getNumberOfTestClasses() + 1);
                reader.close();

                // Overwrites the file with new data
                Writer writer = new FileWriter(path, false);
                Gson gsonWriter = new GsonBuilder().setPrettyPrinting().create();
                gsonWriter.toJson(projectInfo, writer);
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                // Creates a new file
                Writer writer = new FileWriter(path, false);
                Gson gsonWriter = new GsonBuilder().setPrettyPrinting().create();
                ProjectInfo projectInfo = new ProjectInfo(psiClassBean.getProjectName(), 1);
                gsonWriter.toJson(projectInfo, writer);
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @GetMapping("/getAllClasses")
    public List<PsiClassBean> getAllPsiClassBeans() {
        return psiClassBeanService.getAllPsiClassBeans();
    }

    @GetMapping("/deleteAllClasses")
    public int deleteAllPsiClassBeans() {
        return psiClassBeanService.deleteAllPsiClassBeans();
    }

    @GetMapping("/getClassesWithEagerTest")
    public List<PsiClassBean> getClassesWithEagerTest() {
        List<PsiClassBean> classesWithEagerTest = new ArrayList<>();
        for (PsiClassBean testClass: psiClassBeanService.getAllPsiClassBeans()) {
            if (EagerTestTextual.isEagerTest(testClass, testClass.getProductionClass())) {
                classesWithEagerTest.add(testClass);
            }
        }
        return classesWithEagerTest;
    }

    @GetMapping("/getMethodsWithEagerTest")
    public List<MethodWithEagerTest> getMethodsWithEagerTest() {
        List<MethodWithEagerTest> methodsWithEagerTest = new ArrayList<>();
        for (PsiClassBean testClass: psiClassBeanService.getAllPsiClassBeans()) {
            methodsWithEagerTest.addAll(EagerTestTextual.checkMethodsThatCauseEagerTest(testClass,
                    testClass.getProductionClass()));
        }
        return methodsWithEagerTest;
    }

    @GetMapping("/getClassesWithGeneralFixture")
    public List<PsiClassBean> getClassesWithGeneralFixture() {
        List<PsiClassBean> classesWithGeneralFixture = new ArrayList<>();
        for (PsiClassBean testClass: psiClassBeanService.getAllPsiClassBeans()) {
            if (GeneralFixtureTextual.isGeneralFixture(testClass)) {
                classesWithGeneralFixture.add(testClass);
            }
        }
        return classesWithGeneralFixture;
    }

    @GetMapping("/getMethodsWithGeneralFixture")
    public List<MethodWithGeneralFixture> getMethodsWithGeneralFixture() {
        List<MethodWithGeneralFixture> methodsWithGeneralFixture = new ArrayList<>();
        for (PsiClassBean testClass: psiClassBeanService.getAllPsiClassBeans()) {
            methodsWithGeneralFixture.addAll(
                    GeneralFixtureTextual.checkMethodsThatCauseGeneralFixture(testClass));
        }
        return methodsWithGeneralFixture;
    }

    @GetMapping("/getClassesWithLackOfCohesion")
    public List<PsiClassBean> getClassesWithLackOfCohesion() {
        List<PsiClassBean> classesWithLackOfCohesion = new ArrayList<>();
        for (PsiClassBean testClass: psiClassBeanService.getAllPsiClassBeans()) {
            if (LackOfCohesionOfTestSmellStructural.isLackOfCohesion(testClass)) {
                classesWithLackOfCohesion.add(testClass);
            }
        }
        return classesWithLackOfCohesion;
    }

    @GetMapping("/deleteProjectResults")
    public void deleteProjectResults(@RequestParam String projectName) {
        Path path = Paths.get(resultsDirectory + projectName + ".json");
        if (Files.exists(path)) {
            try {
                Files.delete(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
