package net.nvcm.dao.implementation;

import net.nvcm.dao.GenericAbstractDAO;
import net.nvcm.dao.interfaces.ICompanyDAO;
import net.nvcm.dao.interfaces.IEmployeeDAO;
import net.nvcm.entities.CompanyEntity;
import net.nvcm.entities.EmployeeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by byaxe on 4/8/16.
 */
@Repository
@Transactional(readOnly = true)
public class CompanyDAOImpl extends GenericAbstractDAO implements ICompanyDAO {

    /*TODO is it good?*/
    @Autowired
    private IEmployeeDAO employeeDAO;

    @Override
    public List<CompanyEntity> getCompaniesList() {
        return entityManager.createQuery("SELECT c FROM CompanyEntity c",
                CompanyEntity.class).getResultList();
    }

    @Override
    public CompanyEntity getCompanyById(final int id) {
        return entityManager.createQuery("SELECT c FROM CompanyEntity c WHERE c.id = :id",
                CompanyEntity.class).setParameter("id", id).getSingleResult();
    }


    @Override
    public List<CompanyEntity> getCompaniesListById(final int id) {
        return entityManager.createQuery("SELECT c FROM CompaniesEntity c JOIN" +
                " c.employees e WHERE e.id = :id", CompanyEntity.class).setParameter("id", id)
                .getResultList();
    }

    @Override
    @Transactional
    public CompanyEntity saveCompany(final CompanyEntity company) {
        entityManager.persist(company);
        return company;
    }

    @Override
    @Transactional
    public CompanyEntity saveCompanyToEmployee(final int employee_id, final CompanyEntity company) {

        EmployeeEntity employee = employeeDAO.getEmployeeById(employee_id);

        Set<CompanyEntity> companies = employee.getCompanies();

        companies.add(company);

        employee.setCompanies(companies);

        entityManager.persist(employee);

        return company;
    }

    @Override
    @Transactional
    public CompanyEntity removeCompany(final int id) {
        CompanyEntity removedCompany = entityManager.createQuery("SELECT c FROM CompanyEntity c" +
                " WHERE c.id = :id", CompanyEntity.class)
                .setParameter("id", id).getSingleResult();

        entityManager.remove(removedCompany);

        return removedCompany;

    }

    @Override
    public boolean isCompanyExist(final String title) {
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
}
