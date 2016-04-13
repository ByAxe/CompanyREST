package net.nvcm.dao.interfaces;

import net.nvcm.entities.EmployeeEntity;

import java.util.List;

/**
 * Created by byaxe on 4/8/16.
 */
public interface IEmployeeDAO {

    List<EmployeeEntity> getEmployeeList();

    EmployeeEntity getEmployeeById(final int id);

    List<EmployeeEntity> getEmployeesListById(final int id);

    EmployeeEntity saveEmployee(final EmployeeEntity employee);

    EmployeeEntity saveEmployeeToCompany(final int company_id, final EmployeeEntity employee);

    EmployeeEntity removeEmployee(final int id);

    boolean isEmployeeExist(final EmployeeEntity employee);
}
