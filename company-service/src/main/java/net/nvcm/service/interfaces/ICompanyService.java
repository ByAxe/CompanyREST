package net.nvcm.service.interfaces;

import net.nvcm.core.dto.CompanyDTOFull;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

/**
 * Created by byaxe on 4/8/16.
 */
public interface ICompanyService {

    List<CompanyDTOFull> getCompaniesList();

    CompanyDTOFull getCompanyById(final int id);

    List<CompanyDTOFull> getCompaniesListById(final int id);

    CompanyDTOFull saveCompany(final CompanyDTOFull company);

    CompanyDTOFull saveCompanyToEmployee(final int employee_id, final int company_id);

    CompanyDTOFull removeCompany(final int id);

    @Modifying
    CompanyDTOFull updateCompany(final int id, final CompanyDTOFull company);

    boolean isExist(final CompanyDTOFull company);
}
