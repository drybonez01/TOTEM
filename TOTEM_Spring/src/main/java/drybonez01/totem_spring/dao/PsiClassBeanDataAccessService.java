package drybonez01.totem_spring.dao;

import drybonez01.totem_spring.model.PsiClassBean;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("psiClassBeanDao")
public class PsiClassBeanDataAccessService implements PsiClassBeanDao{
    private static List<PsiClassBean> DB = new ArrayList<>();

    @Override
    public int insertPsiClassBean(PsiClassBean psiClassBean) {
        DB.add(psiClassBean);
        return 1;
    }

    @Override
    public List<PsiClassBean> selectAllPsiClassBeans() {
        return DB;
    }

    @Override
    public int removeAllPsiClassBeans() {
        DB.clear();
        return 1;
    }
}
