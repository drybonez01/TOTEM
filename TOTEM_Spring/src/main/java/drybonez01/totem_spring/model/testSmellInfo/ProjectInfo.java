package drybonez01.totem_spring.model.testSmellInfo;

import drybonez01.totem_spring.model.testSmellInfo.classSmellInfo.ClassWithEagerTest;
import drybonez01.totem_spring.model.testSmellInfo.classSmellInfo.ClassWithGeneralFixture;
import drybonez01.totem_spring.model.testSmellInfo.classSmellInfo.ClassWithLackOfCohesion;

import java.util.ArrayList;
import java.util.List;

public class ProjectInfo {
    private String projectName;
    private int numberOfTestClasses;

    private int numberOfClassesWithEagerTest;
    private int numberOfMethodsCausingEagerTest;
    private List<ClassWithEagerTest> classesWithEagerTest;

    private int numberOfClassesWithGeneralFixture;
    private int numberOfMethodsCausingGeneralFixture;
    private List<ClassWithGeneralFixture> classesWithGeneralFixture;

    private int numberOfClassesWithLackOfCohesion;
    private List<ClassWithLackOfCohesion> classesWithLackOfCohesion;

    public ProjectInfo(String projectName, int numberOfTestClasses) {
        this.projectName = projectName;
        this.numberOfTestClasses = numberOfTestClasses;
        this.classesWithEagerTest = new ArrayList<>();
        this.classesWithGeneralFixture = new ArrayList<>();
        this.classesWithLackOfCohesion = new ArrayList<>();
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getNumberOfTestClasses() {
        return numberOfTestClasses;
    }

    public void setNumberOfTestClasses(int numberOfTestClasses) {
        this.numberOfTestClasses = numberOfTestClasses;
    }

    public int getNumberOfClassesWithEagerTest() {
        return numberOfClassesWithEagerTest;
    }

    public void setNumberOfClassesWithEagerTest(int numberOfClassesWithEagerTest) {
        this.numberOfClassesWithEagerTest = numberOfClassesWithEagerTest;
    }

    public int getNumberOfMethodsCausingEagerTest() {
        return numberOfMethodsCausingEagerTest;
    }

    public void setNumberOfMethodsCausingEagerTest(int numberOfMethodsCausingEagerTest) {
        this.numberOfMethodsCausingEagerTest = numberOfMethodsCausingEagerTest;
    }

    public List<ClassWithEagerTest> getClassesWithEagerTest() {
        return classesWithEagerTest;
    }

    public void setClassesWithEagerTest(List<ClassWithEagerTest> classesWithEagerTest) {
        this.classesWithEagerTest = classesWithEagerTest;
    }

    public int getNumberOfClassesWithGeneralFixture() {
        return numberOfClassesWithGeneralFixture;
    }

    public void setNumberOfClassesWithGeneralFixture(int numberOfClassesWithGeneralFixture) {
        this.numberOfClassesWithGeneralFixture = numberOfClassesWithGeneralFixture;
    }

    public int getNumberOfMethodsCausingGeneralFixture() {
        return numberOfMethodsCausingGeneralFixture;
    }

    public void setNumberOfMethodsCausingGeneralFixture(int numberOfMethodsCausingGeneralFixture) {
        this.numberOfMethodsCausingGeneralFixture = numberOfMethodsCausingGeneralFixture;
    }

    public List<ClassWithGeneralFixture> getClassesWithGeneralFixture() {
        return classesWithGeneralFixture;
    }

    public void setClassesWithGeneralFixture(List<ClassWithGeneralFixture> classesWithGeneralFixture) {
        this.classesWithGeneralFixture = classesWithGeneralFixture;
    }

    public int getNumberOfClassesWithLackOfCohesion() {
        return numberOfClassesWithLackOfCohesion;
    }

    public void setNumberOfClassesWithLackOfCohesion(int numberOfClassesWithLackOfCohesion) {
        this.numberOfClassesWithLackOfCohesion = numberOfClassesWithLackOfCohesion;
    }

    public List<ClassWithLackOfCohesion> getClassesWithLackOfCohesion() {
        return classesWithLackOfCohesion;
    }

    public void setClassesWithLackOfCohesion(List<ClassWithLackOfCohesion> classesWithLackOfCohesion) {
        this.classesWithLackOfCohesion = classesWithLackOfCohesion;
    }

    @Override
    public String toString() {
        return "ProjectInfo{" +
                "projectName='" + projectName + '\'' +
                ", numberOfTestClasses=" + numberOfTestClasses +
                ", numberOfClassesWithEagerTest=" + numberOfClassesWithEagerTest +
                ", numberOfMethodsCausingEagerTest=" + numberOfMethodsCausingEagerTest +
                ", classesWithEagerTest=" + classesWithEagerTest +
                ", numberOfClassesWithGeneralFixture=" + numberOfClassesWithGeneralFixture +
                ", numberOfMethodsCausingGeneralFixture=" + numberOfMethodsCausingGeneralFixture +
                ", classesWithGeneralFixture=" + classesWithGeneralFixture +
                ", numberOfClassesWithLackOfCohesion=" + numberOfClassesWithLackOfCohesion +
                ", classesWithLackOfCohesion=" + classesWithLackOfCohesion +
                '}';
    }
}
