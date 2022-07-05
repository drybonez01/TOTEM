package drybonez01.totem_spring.dao;

import drybonez01.totem_spring.model.PsiClassBean;

import java.util.List;

public interface PsiClassBeanDao {
    int insertPsiClassBean(PsiClassBean psiClassBean);
    List<PsiClassBean> selectAllPsiClassBeans();
    int removeAllPsiClassBeans();
}
