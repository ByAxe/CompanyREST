package net.nvcm.service.interfaces;

import net.nvcm.core.dto.EmployeeDTOFull;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

/**
 * Created by byaxe on 4/8/16.
 */
public interface IEmployeeService {

    List<EmployeeDTOFull> getEmployeeList();

    EmployeeDTOFull getEmployeeById(final int id);

    List<EmployeeDTOFull> getEmployeesListById(final int id);

    EmployeeDTOFull saveEmployee(final EmployeeDTOFull employee);

    EmployeeDTOFull saveEmployeeToCompany(final int company_id, final int employee_id);

    EmployeeDTOFull removeEmployee(final int id);

    @Modifying
    EmployeeDTOFull updateEmployee(final int id, final EmployeeDTOFull employee);

    boolean isExist(final EmployeeDTOFull employee);

}
