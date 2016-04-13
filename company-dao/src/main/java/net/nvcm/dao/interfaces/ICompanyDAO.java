package net.nvcm.dao.interfaces;

import net.nvcm.entities.CompanyEntity;

import java.util.List;

/**
 * Created by byaxe on 4/8/16.
 */
public interface ICompanyDAO {

    List<CompanyEntity> getCompaniesList();

    CompanyEntity getCompanyById(final int id);

    List<CompanyEntity> getCompaniesListById(final int id);

    CompanyEntity saveCompany(final CompanyEntity company);

    CompanyEntity saveCompanyToEmployee(final int employee_id, final CompanyEntity company);

    CompanyEntity removeCompany(final int id);

    boolean isCompanyExist(final CompanyEntity company);
}
