package net.nvcm.dao.interfaces;

import net.nvcm.entities.CompanyEntity;
import net.nvcm.entities.EmployeeEntity;

import java.util.List;

/**
 * Created by byaxe on 4/8/16.
 */
public interface IEmployeeDAO {

    List<EmployeeEntity> getEmployeeList();

    EmployeeEntity getEmployeeById(final int id);

    List<CompanyEntity> getCompaniesListById(final int id);

    EmployeeEntity saveEmployee(final EmployeeEntity employee);

    CompanyEntity saveCompanyToEmployee(final int employee_id, final CompanyEntity company);

    EmployeeEntity removeEmployee(final int id);

    boolean isEmployeeExist(final EmployeeEntity employee);
}
