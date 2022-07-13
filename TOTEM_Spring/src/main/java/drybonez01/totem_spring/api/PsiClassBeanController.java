package drybonez01.totem_spring.api;

import drybonez01.totem_spring.model.PsiClassBean;
import drybonez01.totem_spring.model.detectionTools.structuralRules.LackOfCohesionOfTestSmellStructural;
import drybonez01.totem_spring.model.detectionTools.textualRules.EagerTestTextual;
import drybonez01.totem_spring.model.detectionTools.textualRules.GeneralFixtureTextual;
import drybonez01.totem_spring.model.testSmellInfo.methodSmellInfo.MethodWithEagerTest;
import drybonez01.totem_spring.model.testSmellInfo.methodSmellInfo.MethodWithGeneralFixture;
import drybonez01.totem_spring.service.PsiClassBeanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("api/v1/psi")
@RestController
public class PsiClassBeanController {
    //TODO sviluppare un sistema in grado di salvare in una directory locale informazioni sui test smell
    //TODO salvare tali informazioni in un json
    private final PsiClassBeanService psiClassBeanService;

    @Autowired
    public PsiClassBeanController(PsiClassBeanService psiClassBeanService) {
        this.psiClassBeanService = psiClassBeanService;
    }

    @PostMapping
    public void addPsiClassBean(@RequestBody PsiClassBean psiClassBean) {
        System.out.println("Oggetto ricevuto");
        psiClassBeanService.addPsiClassBean(psiClassBean);
    }

    @GetMapping("/getAllClasses")
    public List<PsiClassBean> getAllPsiClassBeans() {
        return psiClassBeanService.getAllPsiClassBeans();
    }

    @GetMapping("/deleteAllClasses")
    public int deleteAllPsiClassBeans() {
        return psiClassBeanService.deleteAllPsiClassBeans();
    }

    @GetMapping("/getClassesWithEagerTest")
    public List<PsiClassBean> getClassesWithEagerTest() {
        List<PsiClassBean> classesWithEagerTest = new ArrayList<>();
        for (PsiClassBean testClass: psiClassBeanService.getAllPsiClassBeans()) {
            if (EagerTestTextual.isEagerTest(testClass, testClass.getProductionClass())) {
                classesWithEagerTest.add(testClass);
            }
        }
        return classesWithEagerTest;
    }

    @GetMapping("/getMethodsWithEagerTest")
    public List<MethodWithEagerTest> getMethodsWithEagerTest() {
        List<MethodWithEagerTest> methodsWithEagerTest = new ArrayList<>();
        for (PsiClassBean testClass: psiClassBeanService.getAllPsiClassBeans()) {
            methodsWithEagerTest.addAll(EagerTestTextual.checkMethodsThatCauseEagerTest(testClass,
                    testClass.getProductionClass()));
        }
        return methodsWithEagerTest;
    }

    @GetMapping("/getClassesWithGeneralFixture")
    public List<PsiClassBean> getClassesWithGeneralFixture() {
        List<PsiClassBean> classesWithGeneralFixture = new ArrayList<>();
        for (PsiClassBean testClass: psiClassBeanService.getAllPsiClassBeans()) {
            if (GeneralFixtureTextual.isGeneralFixture(testClass)) {
                classesWithGeneralFixture.add(testClass);
            }
        }
        return classesWithGeneralFixture;
    }

    @GetMapping("/getMethodsWithGeneralFixture")
    public List<MethodWithGeneralFixture> getMethodsWithGeneralFixture() {
        List<MethodWithGeneralFixture> methodsWithGeneralFixture = new ArrayList<>();
        for (PsiClassBean testClass: psiClassBeanService.getAllPsiClassBeans()) {
            methodsWithGeneralFixture.addAll(
                    GeneralFixtureTextual.checkMethodsThatCauseGeneralFixture(testClass));
        }
        return methodsWithGeneralFixture;
    }

    @GetMapping("/getClassesWithLackOfCohesion")
    public List<PsiClassBean> getClassesWithLackOfCohesion() {
        List<PsiClassBean> classesWithLackOfCohesion = new ArrayList<>();
        for (PsiClassBean testClass: psiClassBeanService.getAllPsiClassBeans()) {
            if (LackOfCohesionOfTestSmellStructural.isLackOfCohesion(testClass)) {
                classesWithLackOfCohesion.add(testClass);
            }
        }
        return classesWithLackOfCohesion;
    }
}
