package net.nvcm.repository.interfaces;

import net.nvcm.entities.CompanyEntity;

import java.util.List;

/**
 * Created by byaxe on 4/13/16.
 */
public interface ICompanyRepositoryCustom {

    boolean isExist(final String title);

    List<CompanyEntity> getCompaniesListById(final int id);

    CompanyEntity saveCompanyToEmployee(final int employee_id, final CompanyEntity company);

}
