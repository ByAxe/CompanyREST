package net.nvcm.service.interfaces;

import net.nvcm.core.dto.EmployeeDTOFull;

import java.util.List;

/**
 * Created by byaxe on 4/8/16.
 */
public interface IEmployeeService {

    List<EmployeeDTOFull> getEmployeeList();

    EmployeeDTOFull getEmployeeById(final int id);

    List<EmployeeDTOFull> getEmployeesListById(final int id);

    EmployeeDTOFull saveEmployee(final EmployeeDTOFull employee);

    EmployeeDTOFull saveEmployeeToCompany(final int company_id, final EmployeeDTOFull employee);

    EmployeeDTOFull removeEmployee(final int id);

    boolean isEmployeeExist(final EmployeeDTOFull employee);

}
