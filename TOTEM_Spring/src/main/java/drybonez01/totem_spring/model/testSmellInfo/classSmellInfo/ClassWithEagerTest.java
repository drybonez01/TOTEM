package drybonez01.totem_spring.model.testSmellInfo.classSmellInfo;

import drybonez01.totem_spring.model.PsiClassBean;
import drybonez01.totem_spring.model.testSmellInfo.methodSmellInfo.MethodWithEagerTest;

import java.util.List;

public class ClassWithEagerTest {
    private PsiClassBean classWithEagerTest;
    private List<MethodWithEagerTest> methodsWithEagerTest;

    public ClassWithEagerTest(PsiClassBean classWithEagerTest, List<MethodWithEagerTest> methodsWithEagerTest) {
        this.classWithEagerTest = classWithEagerTest;
        this.methodsWithEagerTest = methodsWithEagerTest;
    }

    public PsiClassBean getClassWithEagerTest() {
        return classWithEagerTest;
    }

    public void setClassWithEagerTest(PsiClassBean classWithEagerTest) {
        this.classWithEagerTest = classWithEagerTest;
    }

    public List<MethodWithEagerTest> getMethodsWithEagerTest() {
        return methodsWithEagerTest;
    }

    public void setMethodsWithEagerTest(List<MethodWithEagerTest> methodsWithEagerTest) {
        this.methodsWithEagerTest = methodsWithEagerTest;
    }
}
