package drybonez01.totem_spring.model.testSmellInfo.methodSmellInfo;

import drybonez01.totem_spring.model.PsiMethodBean;

import java.util.ArrayList;

public class MethodWithGeneralFixture {
    private PsiMethodBean methodWithGeneralFixture;
    private ArrayList<String> variablesNotInMethod;

    public MethodWithGeneralFixture(PsiMethodBean methodWithGeneralFixture,
                                    ArrayList<String> variablesNotInMethod) {
        this.methodWithGeneralFixture = methodWithGeneralFixture;
        this.variablesNotInMethod = variablesNotInMethod;
    }

    /* GETTERS & SETTERS */
    public PsiMethodBean getMethodWithGeneralFixture() {
        return methodWithGeneralFixture;
    }

    public void setMethodWithGeneralFixture(PsiMethodBean methodWithGeneralFixture) {
        this.methodWithGeneralFixture = methodWithGeneralFixture;
    }

    public ArrayList<String> getVariablesNotInMethod() {
        return variablesNotInMethod;
    }

    public void setVariablesNotInMethod(ArrayList<String> variablesNotInMethod) {
        this.variablesNotInMethod = variablesNotInMethod;
    }
}
