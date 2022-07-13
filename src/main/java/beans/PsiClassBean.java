package beans;
import java.util.ArrayList;

public class PsiClassBean {
    private String projectName;
    private String className;
    private String packageName;
    private ArrayList<PsiMethodBean> psiMethodBeans;
    private ArrayList<String> instanceVariables;

    private PsiClassBean productionClass;

    public PsiClassBean(String projectName,
                        String className,
                        String packageName,
                        ArrayList<PsiMethodBean> psiMethodBeans,
                        ArrayList<String> instanceVariables,
                        PsiClassBean productionClass) {
        this.projectName = projectName;
        this.className = className;
        this.packageName = packageName;
        this.psiMethodBeans = psiMethodBeans;
        this.instanceVariables = instanceVariables;
        this.productionClass = productionClass;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public ArrayList<PsiMethodBean> getPsiMethodBeans() {
        return psiMethodBeans;
    }

    public void setPsiMethodBeans(ArrayList<PsiMethodBean> psiMethodBeans) {
        this.psiMethodBeans = psiMethodBeans;
    }

    public ArrayList<String> getInstanceVariables() {
        return instanceVariables;
    }

    public void setInstanceVariables(ArrayList<String> instanceVariables) {
        this.instanceVariables = instanceVariables;
    }

    public PsiClassBean getProductionClass() {
        return productionClass;
    }

    public void setProductionClass(PsiClassBean productionClass) {
        this.productionClass = productionClass;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Override
    public String toString() {
        return "PsiClassBean{" +
                "projectName='" + projectName + '\'' +
                ", className='" + className + '\'' +
                ", packageName='" + packageName + '\'' +
                ", psiMethodBeans=" + psiMethodBeans +
                ", instanceVariables=" + instanceVariables +
                ", productionClass=" + productionClass +
                '}';
    }
}