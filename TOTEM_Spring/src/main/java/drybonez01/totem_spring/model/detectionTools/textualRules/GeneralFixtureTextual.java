package drybonez01.totem_spring.model.detectionTools.textualRules;

import drybonez01.totem_spring.model.PsiClassBean;
import drybonez01.totem_spring.model.PsiMethodBean;
import drybonez01.totem_spring.model.testSmellInfo.MethodWithGeneralFixture;

import java.util.ArrayList;

public abstract class GeneralFixtureTextual {
    public static boolean isGeneralFixture(PsiClassBean testClass) {
        ArrayList<PsiMethodBean> methods = testClass.getPsiMethodBeans();
        for (PsiMethodBean methodBean: methods) {
            if (methodBean.getMethodName().toLowerCase().equals("setup")) {
                ArrayList<String> instanceVariableInitSetup = methodBean.getInitInstanceVariables();
                if (instanceVariableInitSetup.size() > 0) {
                    for (PsiMethodBean psiMethodBeanInside: methods) {
                        String methodName = psiMethodBeanInside.getMethodName();
                        if (!methodName.equals(testClass.getClassName()) &&
                                !methodName.toLowerCase().equals("setup") &&
                                !methodName.toLowerCase().equals("teardown")) {
                            ArrayList<String> instanceVariableUses =
                                    psiMethodBeanInside.getUsedInstanceVariables();
                            for (String psiInstanceVariable: instanceVariableInitSetup) {
                                if (!instanceVariableUses.contains(psiInstanceVariable)) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public static ArrayList<MethodWithGeneralFixture> checkMethodsThatCauseGeneralFixture(
            PsiClassBean testClass) {
        ArrayList<MethodWithGeneralFixture> methodWithGeneralFixtures = new ArrayList<>();
        ArrayList<PsiMethodBean> methods = testClass.getPsiMethodBeans();
        for (PsiMethodBean methodBean: methods) {
            if (methodBean.getMethodName().toLowerCase().equals("setup")) {
                ArrayList<String> instanceVariableInitSetup =
                        methodBean.getInitInstanceVariables();
                if (instanceVariableInitSetup.size() > 0) {
                    for (PsiMethodBean psiMethodBeanInside: methods) {
                        String methodName = psiMethodBeanInside.getMethodName();
                        if (!methodName.equals(testClass.getClassName()) &&
                                !methodName.toLowerCase().equals("setup") &&
                                !methodName.toLowerCase().equals("teardown")) {
                            boolean hasGeneralFixture = false;
                            ArrayList<String> instanceVariableNotUsed = new ArrayList<>();

                            ArrayList<String> instanceVariableUses =
                                    psiMethodBeanInside.getUsedInstanceVariables();
                            for (String instanceVariable: instanceVariableInitSetup) {
                                if (!instanceVariableUses.contains(instanceVariable)) {
                                    hasGeneralFixture = true;
                                    instanceVariableNotUsed.add(instanceVariable);
                                }
                            }
                            if (hasGeneralFixture) {
                                MethodWithGeneralFixture methodWithGeneralFixture =
                                        new MethodWithGeneralFixture(psiMethodBeanInside,
                                                instanceVariableNotUsed);
                                methodWithGeneralFixtures.add(methodWithGeneralFixture);
                            }
                        }
                    }
                }
            }
        }

        return methodWithGeneralFixtures;
    }
}
