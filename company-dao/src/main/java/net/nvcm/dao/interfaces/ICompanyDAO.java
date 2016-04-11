package net.nvcm.dao.interfaces;

import net.nvcm.entities.CompanyEntity;
import net.nvcm.entities.EmployeeEntity;

import java.util.List;

/**
 * Created by byaxe on 4/8/16.
 */
public interface ICompanyDAO {

    List<CompanyEntity> getCompaniesList();

    CompanyEntity getCompanyById(final int id);

    List<EmployeeEntity> getEmployeesListById(final int id);

    CompanyEntity saveCompany(final CompanyEntity company);

    EmployeeEntity saveEmployee(final EmployeeEntity employee, final CompanyEntity company);

    CompanyEntity removeCompany(final int id);

    boolean isCompanyExist(final CompanyEntity company);
}
