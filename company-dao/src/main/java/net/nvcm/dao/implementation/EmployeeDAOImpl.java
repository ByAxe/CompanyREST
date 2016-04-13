package net.nvcm.dao.implementation;

import net.nvcm.dao.GenericAbstractDAO;
import net.nvcm.dao.interfaces.ICompanyDAO;
import net.nvcm.dao.interfaces.IEmployeeDAO;
import net.nvcm.entities.CompanyEntity;
import net.nvcm.entities.EmployeeEntity;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private ICompanyDAO companyDAO;

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
    public List<EmployeeEntity> getEmployeesListById(final int id) {
        return entityManager.createQuery("SELECT e FROM EmployeeEntity e JOIN" +
                " e.companies c WHERE c.id = :id", EmployeeEntity.class).setParameter("id", id)
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
    public EmployeeEntity saveEmployeeToCompany(final int company_id, final EmployeeEntity employee) {
        CompanyEntity company = companyDAO.getCompanyById(company_id);

        Set<EmployeeEntity> employees = company.getEmployees();

        employees.add(employee);

        company.setEmployees(employees);

        entityManager.persist(company);

        return employee;
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
