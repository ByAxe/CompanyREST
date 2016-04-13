package net.nvcm.repository.interfaces;

import net.nvcm.entities.EmployeeEntity;

import java.util.List;

/**
 * Created by byaxe on 4/13/16.
 */
public interface IEmployeeRepositoryCustom {

    boolean isExist(final EmployeeEntity employeeEntity);

    EmployeeEntity saveEmployeeToCompany(final int company_id, final EmployeeEntity employee);

    List<EmployeeEntity> getEmployeesListById(final int id);


}
