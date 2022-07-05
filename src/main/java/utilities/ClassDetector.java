package utilities;

import com.intellij.psi.PsiClass;

import java.util.List;

public abstract class ClassDetector {
    public static boolean isTestClass(PsiClass psiClass) {
        return psiClass.getName().contains("Test")
                || psiClass.getName().contains("test")
                || psiClass.getText().contains(" extends TestCase");
    }

    public static PsiClass findProductionClass(PsiClass psiTestClass, List<PsiClass> allClasses) {
        for(PsiClass psiClass : allClasses){
            String candidateTestName = psiClass.getName()+"Test";
            String candidateTestName2 = "Test"+psiClass.getName();
            String candidateTestName3 = "Tests"+psiClass.getName();
            String candidateTestName4 = psiClass.getName()+"Tests";
            String candidateTestName5 = "TC_"+psiClass.getName();
            String candidateTestName6 = psiClass.getName()+"_TC";
            String candidateTestName7 = psiClass.getName().replaceAll("_", "")+"Test";
            String candidateTestName8 = "Test"+psiClass.getName().replaceAll("_", "");
            String candidateTestName9 = psiClass.getName()+"TestCase";
            String candidateTestName10 = "TestCase"+psiClass.getName();
            if(candidateTestName.equalsIgnoreCase(psiTestClass.getName())) {
                return psiClass;
            } else if(candidateTestName2.equalsIgnoreCase(psiTestClass.getName())) {
                return psiClass;
            } else if(candidateTestName3.equalsIgnoreCase(psiTestClass.getName())) {
                return psiClass;
            } else if(candidateTestName4.equalsIgnoreCase(psiTestClass.getName())) {
                return psiClass;
            } else if(candidateTestName5.equalsIgnoreCase(psiTestClass.getName())) {
                return psiClass;
            } else if(candidateTestName6.equalsIgnoreCase(psiTestClass.getName())) {
                return psiClass;
            } else if(candidateTestName7.equalsIgnoreCase(psiTestClass.getName())) {
                return psiClass;
            } else if(candidateTestName8.equalsIgnoreCase(psiTestClass.getName())) {
                return psiClass;
            } else if(candidateTestName9.equalsIgnoreCase(psiTestClass.getName())) {
                return psiClass;
            } else if(candidateTestName10.equalsIgnoreCase(psiTestClass.getName())) {
                return psiClass;
            }
        }
        return null;
    }
}
