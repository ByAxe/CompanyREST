package net.nvcm.dao.implementation;

import net.nvcm.dao.GenericAbstractDAO;
import net.nvcm.dao.interfaces.ICompanyDAO;
import net.nvcm.entities.CompanyEntity;
import net.nvcm.entities.EmployeeEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * Created by byaxe on 4/8/16.
 */

@Repository
@Transactional(readOnly = true)
public class CompanyDAOImpl extends GenericAbstractDAO implements ICompanyDAO {

    @Override
    public List<CompanyEntity> getCompaniesList() {
        return entityManager.createQuery("SELECT c FROM CompanyEntity c",
                CompanyEntity.class).getResultList();
    }

    @Override
    public CompanyEntity getCompanyById(final int id) {
        return entityManager.createQuery("SELECT c FROM CompanyEntity c WHERE id = :id",
                CompanyEntity.class).setParameter("id", id).getSingleResult();
    }

    @Override
    public List<EmployeeEntity> getEmployeesListById(final int id) {
        return entityManager.createQuery("SELECT e FROM EmployeeEntity e JOIN" +
                " e.companies c WHERE c.id = :id", EmployeeEntity.class).setParameter("id", id)
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
    public EmployeeEntity saveEmployeeToCompany(final int company_id, final EmployeeEntity employee) {
        CompanyEntity company = getCompanyById(company_id);

        Set<EmployeeEntity> employees = company.getEmployees();

        employees.add(employee);

        company.setEmployees(employees);

        /*TODO brave-new company?*/
        entityManager.persist(company);

        return employee;
    }

    @Override
    @Transactional
    public CompanyEntity removeCompany(final int id) {
        CompanyEntity removedCompany = entityManager
                .createQuery("SELECT c FROM CompanyEntity c WHERE id = :id",
                        CompanyEntity.class).setParameter("id", id).getSingleResult();

        entityManager.remove(removedCompany);

        return removedCompany;

    }

    @Override
    public boolean isCompanyExist(final CompanyEntity company) {
        return entityManager.createQuery("SELECT c FROM CompanyEntity c" +
                " WHERE c.title = :title AND c.employees = :employees", CompanyEntity.class)
                .setParameter("title", company.getTitle())
                .setParameter("employees", company.getEmployees())
                .getResultList() != null;
    }
}
