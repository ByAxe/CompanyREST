package net.nvcm.repository.implementation;

import net.nvcm.entities.CompanyEntity;
import net.nvcm.entities.EmployeeEntity;
import net.nvcm.repository.GenericAbstractDAO;
import net.nvcm.repository.interfaces.CompanyRepository;
import net.nvcm.repository.interfaces.CompanyRepositoryCustom;
import net.nvcm.repository.interfaces.EmployeeRepository;
import org.hibernate.envers.Audited;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Set;

/**
 * Created by byaxe on 4/8/16.
 */
@Repository
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class CompanyRepositoryImpl extends GenericAbstractDAO implements CompanyRepositoryCustom {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CompanyRepository companyRepository;

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
    @Transactional
    public CompanyEntity saveCompanyToEmployee(final int employee_id, final int company_id) {
        EmployeeEntity employee = employeeRepository.findOne(employee_id);

        CompanyEntity company = companyRepository.findOne(company_id);

        employee.startWork(company);

        return company;
    }
}
