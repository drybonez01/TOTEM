package testSmellInfo.classSmellInfo;

import beans.PsiClassBean;

public class ClassWithLackOfCohesion {
    private PsiClassBean classWithLackOfCohesion;

    public ClassWithLackOfCohesion(PsiClassBean classWithLackOfCohesion) {
        this.classWithLackOfCohesion = classWithLackOfCohesion;
    }

    public PsiClassBean getClassWithLackOfCohesion() {
        return classWithLackOfCohesion;
    }

    public void setClassWithLackOfCohesion(PsiClassBean classWithLackOfCohesion) {
        this.classWithLackOfCohesion = classWithLackOfCohesion;
    }
}
