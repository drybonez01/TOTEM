package utilities;

import beans.PsiClassBean;
import beans.PsiMethodBean;
import com.intellij.psi.*;

import java.util.ArrayList;

public abstract class BeanGenerator {
    public static PsiClassBean generateClassBean(String projectName,
                                                 PsiClass psiClass,
                                                 PsiClass productionClass) {
        ArrayList<PsiVariable> instanceVariables = PsiTestSmellUtilities.getAllInstanceVariable(psiClass);

        ArrayList<PsiMethodBean> psiMethodBeans = new ArrayList<>();
        for (PsiMethod psiMethod: psiClass.getMethods()) {
            psiMethodBeans.add(generateMethodBean(psiMethod, instanceVariables));
        }

        ArrayList<String> instanceVariablesString = new ArrayList<>();
        for (PsiVariable instanceVariable: instanceVariables) {
            instanceVariablesString.add(instanceVariable.getName());
        }

        PsiClassBean productionClassBean;
        if (productionClass != null) {
            productionClassBean = generateClassBean(projectName, productionClass, null);
        } else {
            productionClassBean = null;
        }

        return new PsiClassBean(
                projectName,
                psiClass.getName(),
                null,
                psiMethodBeans,
                instanceVariablesString,
                productionClassBean);
    }

    public static PsiMethodBean generateMethodBean(PsiMethod psiMethod,
                                             ArrayList<PsiVariable> instanceVariables) {
        ArrayList<String> initInstanceVariables = new ArrayList<>();
        for (PsiVariable psiVariable:
                PsiTestSmellUtilities.getAllInstanceVariableInit(psiMethod, instanceVariables)) {
            initInstanceVariables.add(psiVariable.getName());
        }

        ArrayList<String> usedInstanceVariables = new ArrayList<>();
        for (PsiVariable psiVariable:
                PsiTestSmellUtilities.getAllInstanceVariableUses(psiMethod, instanceVariables)) {
            usedInstanceVariables.add(psiVariable.getName());
        }

        ArrayList<String> methodCalls = new ArrayList<>();
        for (PsiMethodCallExpression psiMethodCallExpression:
                PsiTestSmellUtilities.getAllCalledMethods(psiMethod)) {
            PsiReference psiReference = psiMethodCallExpression.getMethodExpression().getReference();
            if (psiReference != null) {
                PsiMethod calledMethod = (PsiMethod) psiReference.resolve();
                if (calledMethod != null) {
                    methodCalls.add(calledMethod.getName());
                }
            }
        }

        return new PsiMethodBean(
                psiMethod.getName(),
                initInstanceVariables,
                usedInstanceVariables,
                methodCalls
        );
    }
}
