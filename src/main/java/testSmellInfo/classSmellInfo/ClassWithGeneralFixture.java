package testSmellInfo.classSmellInfo;

import beans.PsiClassBean;
import testSmellInfo.methodSmellInfo.MethodWithGeneralFixture;

import java.util.List;

public class ClassWithGeneralFixture {
    private PsiClassBean classWithGeneralFixture;
    private List<MethodWithGeneralFixture> methodsWithGeneralFixture;

    public ClassWithGeneralFixture(PsiClassBean classWithGeneralFixture, List<MethodWithGeneralFixture> methodsWithGeneralFixture) {
        this.classWithGeneralFixture = classWithGeneralFixture;
        this.methodsWithGeneralFixture = methodsWithGeneralFixture;
    }

    public PsiClassBean getClassWithGeneralFixture() {
        return classWithGeneralFixture;
    }

    public void setClassWithGeneralFixture(PsiClassBean classWithGeneralFixture) {
        this.classWithGeneralFixture = classWithGeneralFixture;
    }

    public List<MethodWithGeneralFixture> getMethodsWithGeneralFixture() {
        return methodsWithGeneralFixture;
    }

    public void setMethodsWithGeneralFixture(List<MethodWithGeneralFixture> methodsWithGeneralFixture) {
        this.methodsWithGeneralFixture = methodsWithGeneralFixture;
    }
}
