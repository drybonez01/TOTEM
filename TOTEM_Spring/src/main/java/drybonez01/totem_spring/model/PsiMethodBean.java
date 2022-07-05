package drybonez01.totem_spring.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class PsiMethodBean {
    private String methodName;
    private ArrayList<String> initInstanceVariables;
    private ArrayList<String> usedInstanceVariables;
    private ArrayList<String> methodCalls;

    public PsiMethodBean(@JsonProperty("methodName") String methodName,
                         @JsonProperty("initInstanceVariables") ArrayList<String> initInstanceVariables,
                         @JsonProperty("usedInstanceVariables") ArrayList<String> usedInstanceVariables,
                         @JsonProperty("methodCalls") ArrayList<String> methodCalls) {
        this.methodName = methodName;
        this.initInstanceVariables = initInstanceVariables;
        this.usedInstanceVariables = usedInstanceVariables;
        this.methodCalls = methodCalls;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public ArrayList<String> getInitInstanceVariables() {
        return initInstanceVariables;
    }

    public void setInitInstanceVariables(ArrayList<String> initInstanceVariables) {
        this.initInstanceVariables = initInstanceVariables;
    }

    public ArrayList<String> getUsedInstanceVariables() {
        return usedInstanceVariables;
    }

    public void setUsedInstanceVariables(ArrayList<String> usedInstanceVariables) {
        this.usedInstanceVariables = usedInstanceVariables;
    }

    public ArrayList<String> getMethodCalls() {
        return methodCalls;
    }

    public void setMethodCalls(ArrayList<String> methodCalls) {
        this.methodCalls = methodCalls;
    }

    @Override
    public String toString() {
        return "PsiMethodBean{" +
                "methodName='" + methodName + '\'' +
                ", initInstanceVariables=" + initInstanceVariables +
                ", usedInstanceVariables=" + usedInstanceVariables +
                ", methodCalls=" + methodCalls +
                '}';
    }
}