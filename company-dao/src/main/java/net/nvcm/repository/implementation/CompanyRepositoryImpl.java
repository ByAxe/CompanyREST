package net.nvcm.repository.implementation;

import net.nvcm.entities.CompanyEntity;
import net.nvcm.repository.GenericAbstractDAO;
import net.nvcm.repository.interfaces.ICompanyRepositoryCustom;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by byaxe on 4/8/16.
 */
@Repository
@Transactional(readOnly = true)
public class CompanyRepositoryImpl extends GenericAbstractDAO implements ICompanyRepositoryCustom {

    @Override
    public boolean isExist(final String title) {
        CompanyEntity company = null;

        TypedQuery<CompanyEntity> typedQuery = entityManager.createQuery("SELECT c FROM CompanyEntity c" +
                " WHERE c.title = :title", CompanyEntity.class);

        typedQuery.setParameter("title", title);

        try {
            company = typedQuery.getSingleResult();
        } catch (Throwable throwable) {
//            throwable.printStackTrace();
        }

        return company != null;
    }

    @Override
    public List<CompanyEntity> getCompaniesListById(final int id) {
        return entityManager.createQuery("SELECT c FROM CompaniesEntity c JOIN" +
                " c.employees e WHERE e.id = :id", CompanyEntity.class).setParameter("id", id)
                .getResultList();
    }

    @Override
    public CompanyEntity saveCompanyToEmployee(final int employee_id, final CompanyEntity company) {
        return null;
    }
}
