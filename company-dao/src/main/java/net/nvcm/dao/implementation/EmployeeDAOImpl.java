package net.nvcm.dao.implementation;

import net.nvcm.dao.GenericAbstractDAO;
import net.nvcm.dao.interfaces.IEmployeeDAO;
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
public class EmployeeDAOImpl extends GenericAbstractDAO implements IEmployeeDAO {

    @Override
    public EmployeeEntity getEmployeeById(final int id) {
        return entityManager.createQuery("SELECT e FROM EmployeeEntity e WHERE id = :id",
                EmployeeEntity.class).setParameter("id", id).getSingleResult();
    }

    @Override
    public List<EmployeeEntity> getEmployeeList() {
        return entityManager.createQuery("SELECT e FROM EmployeeEntity e",
                EmployeeEntity.class).getResultList();
    }

    @Override
    public List<CompanyEntity> getCompaniesListById(final int id) {
        return entityManager.createQuery("SELECT c FROM CompaniesEntity c JOIN" +
                " c.employees e WHERE e.id = :id", CompanyEntity.class).setParameter("id", id)
                .getResultList();
    }

    @Override
    @Transactional
    public EmployeeEntity saveEmployee(final EmployeeEntity employee) {
        entityManager.persist(employee);
        return employee;
    }

    @Override
    @Transactional
    public CompanyEntity saveCompanyToEmployee(final int employee_id, final CompanyEntity company) {

        EmployeeEntity employee = this.getEmployeeById(employee_id);

        Set<CompanyEntity> companies = employee.getCompanies();

        companies.add(company);

        employee.setCompanies(companies);

        /*TODO brave-new employee?*/
        entityManager.persist(employee);

        return company;
    }

    @Override
    @Transactional
    public EmployeeEntity removeEmployee(final int id) {
        EmployeeEntity removedEmployee = entityManager
                .createQuery("SELECT e FROM EmployeeEntity e WHERE e.id = :id",
                        EmployeeEntity.class).setParameter("id", id).getSingleResult();

        entityManager.remove(removedEmployee);

        return removedEmployee;
    }


    /*TODO may not work*/
    @Override
    public boolean isEmployeeExist(final EmployeeEntity employee) {
        return entityManager.createQuery("SELECT e FROM EmployeeEntity e" +
                " WHERE e.name = :name AND e.position = :position" +
                " AND e.sex = :sex AND e.age = :age" +
                " AND e.companies = :companies", EmployeeEntity.class)
                .setParameter("name", employee.getName())
                .setParameter("position", employee.getPosition())
                .setParameter("sex", employee.getSex())
                .setParameter("age", employee.getAge())
                .setParameter("companies", employee.getCompanies())
                .getSingleResult() != null;
    }
}
