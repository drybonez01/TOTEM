package drybonez01.totem_spring.service;

import drybonez01.totem_spring.dao.PsiClassBeanDao;
import drybonez01.totem_spring.model.PsiClassBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PsiClassBeanService {
    private final PsiClassBeanDao psiClassBeanDao;

    @Autowired
    public PsiClassBeanService(@Qualifier("psiClassBeanDao") PsiClassBeanDao psiClassBeanDao) {
        this.psiClassBeanDao = psiClassBeanDao;
    }

    public int addPsiClassBean(PsiClassBean psiClassBean) {
        return psiClassBeanDao.insertPsiClassBean(psiClassBean);
    }

    public List<PsiClassBean> getAllPsiClassBeans() {
        return psiClassBeanDao.selectAllPsiClassBeans();
    }

    public int deleteAllPsiClassBeans() {
        return psiClassBeanDao.removeAllPsiClassBeans();
    }
}
