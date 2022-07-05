package drybonez01.totem_spring.model.detectionTools.structuralRules;

import drybonez01.totem_spring.model.PsiClassBean;
import drybonez01.totem_spring.model.PsiMethodBean;

import java.util.ArrayList;
import java.util.List;

public class LackOfCohesionOfTestSmellStructural {
    public static boolean isLackOfCohesion(PsiClassBean testClass) {
        return getLCOM(testClass) > 1;
    }

    public static int getLCOM(PsiClassBean testClass) {
        int share = 0;
        int notShare = 0;
        List<PsiMethodBean> methods = new ArrayList<>();

        for (PsiMethodBean psiMethodBeanInside: testClass.getPsiMethodBeans()) {
            String methodName = psiMethodBeanInside.getMethodName();
            if (!methodName.equals(testClass.getClassName()) &&
                    !methodName.toLowerCase().equals("setup") &&
                    !methodName.toLowerCase().equals("teardown")) {
                methods.add(psiMethodBeanInside);
            }
        }

        for (int i=0; i < methods.size(); i++) {
            for (int j=i+1; j < methods.size(); j++) {
                if (shareAnInstanceVariable(methods.get(i), methods.get(j))) {
                    share++;
                } else {
                    notShare++;
                }
            }
        }

        if (share > notShare) {
            return 0;
        } else {
            return (notShare - share);
        }
    }

    private static boolean shareAnInstanceVariable(PsiMethodBean m1, PsiMethodBean m2) {
        List<String> m1Variables = m1.getUsedInstanceVariables();
        List<String> m2Variables = m2.getUsedInstanceVariables();

        for (String var: m1Variables) {
            if (m2Variables.contains(var)) {
                return true;
            }
        }
        return false;
    }
}
