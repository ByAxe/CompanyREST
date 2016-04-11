package net.nvcm.service.interfaces;

import net.nvcm.entities.CompanyEntity;
import net.nvcm.entities.EmployeeEntity;

import java.util.List;

/**
 * Created by byaxe on 4/8/16.
 */
public interface IEmployeeService {

    EmployeeEntity getEmployeeById(final int id);

    List<EmployeeEntity> getEmployeeList();

    EmployeeEntity saveEmployee(final EmployeeEntity employee);

    CompanyEntity saveCompany(final CompanyEntity company, final EmployeeEntity employee);

    EmployeeEntity removeEmployee(final int id);

    boolean isEmployeeExist(final EmployeeEntity employee);

}
