package net.nvcm.service.interfaces;

import net.nvcm.core.dto.CompanyDTOFull;
import net.nvcm.entities.CompanyEntity;

import java.util.List;

/**
 * Created by byaxe on 4/8/16.
 */
public interface ICompanyService {

    List<CompanyDTOFull> getCompaniesList();

    CompanyDTOFull getCompanyById(final int id);

    List<CompanyDTOFull> getCompaniesListById(final int id);

    CompanyDTOFull saveCompany(final CompanyDTOFull company);

    CompanyEntity saveCompanyToEmployee(final int employee_id, final CompanyDTOFull company);

    CompanyDTOFull removeCompany(final int id);

    boolean isExist(final CompanyDTOFull company);
}
