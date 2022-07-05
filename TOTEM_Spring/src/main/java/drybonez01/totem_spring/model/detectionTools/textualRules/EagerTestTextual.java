package drybonez01.totem_spring.model.detectionTools.textualRules;

import drybonez01.totem_spring.model.PsiClassBean;
import drybonez01.totem_spring.model.PsiMethodBean;
import drybonez01.totem_spring.model.testSmellInfo.MethodWithEagerTest;

import java.util.ArrayList;
import java.util.List;

public abstract class EagerTestTextual {
    public static boolean isEagerTest(PsiClassBean testClass,
                                      PsiClassBean productionClass) {
        for (PsiMethodBean psiMethodBeanInside: testClass.getPsiMethodBeans()) {
            String methodName = psiMethodBeanInside.getMethodName();
            if (!methodName.equals(testClass.getClassName()) &&
                    !methodName.toLowerCase().equals("setup") &&
                    !methodName.toLowerCase().equals("teardown")) {
                List<String> methodCalls = psiMethodBeanInside.getMethodCalls();
                if (methodCalls.size() > 1) {
                    List<PsiMethodBean> methodsInProduction = productionClass.getPsiMethodBeans();
                    int count = 0;
                    for (String methodCall: methodCalls) {
                        for (PsiMethodBean methodInProduction: methodsInProduction) {
                            if (methodCall.toLowerCase().equals(
                                    methodInProduction.getMethodName().toLowerCase())) {
                                count++;
                            }
                        }
                    }
                    if (count > 1) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static List<MethodWithEagerTest> checkMethodsThatCauseEagerTest(PsiClassBean testClass,
                                                                           PsiClassBean productionClass) {
        List<MethodWithEagerTest> methodsWithEagerTests = new ArrayList<>();
        for (PsiMethodBean psiMethodBeanInside: testClass.getPsiMethodBeans()) {
            String methodName = psiMethodBeanInside.getMethodName();
            if (!methodName.equals(testClass.getClassName()) &&
                    !methodName.toLowerCase().equals("setup") &&
                    !methodName.toLowerCase().equals("teardown")) {
                boolean isWithEagerTest = false;
                ArrayList<PsiMethodBean> methodCalledThatCauseEagerTest = new ArrayList<>();
                List<String> methodCalls = psiMethodBeanInside.getMethodCalls();
                if (methodCalls.size() > 1) {
                    List<PsiMethodBean> methodsInProduction = productionClass.getPsiMethodBeans();
                    int count = 0;
                    for (String calledMethod: methodCalls) {
                        for (PsiMethodBean methodInProduction: methodsInProduction) {
                            if (calledMethod.toLowerCase().equals(
                                    methodInProduction.getMethodName().toLowerCase())) {
                                count++;
                                methodCalledThatCauseEagerTest.add(methodInProduction);
                            }
                        }
                    }
                    if (count > 1) {
                        isWithEagerTest = true;
                    }
                    if (isWithEagerTest) {
                        MethodWithEagerTest methodWithEagerTest =
                                new MethodWithEagerTest(psiMethodBeanInside, methodCalledThatCauseEagerTest);
                        methodsWithEagerTests.add(methodWithEagerTest);
                    }
                }
            }
        }
        return methodsWithEagerTests;
    }
}
