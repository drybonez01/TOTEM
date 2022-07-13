package drybonez01.totem_spring.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class PsiClassBean {
    private String projectName;
    private String className;
    private String packageName;
    private ArrayList<PsiMethodBean> psiMethodBeans;
    private ArrayList<String> instanceVariables;
    private PsiClassBean productionClass;

    public PsiClassBean(@JsonProperty("projectName") String projectName,
                        @JsonProperty("className") String className,
                        @JsonProperty("packageName") String packageName,
                        @JsonProperty("psiMethodBeans") ArrayList<PsiMethodBean> psiMethodBeans,
                        @JsonProperty("instanceVariables") ArrayList<String> instanceVariables,
                        @JsonProperty("productionClass") PsiClassBean productionClass) {
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