package net.nvcm.service.implementation;

import net.nvcm.core.dto.CompanyDTOFull;
import net.nvcm.dao.interfaces.ICompanyDAO;
import net.nvcm.dao.interfaces.IEmployeeDAO;
import net.nvcm.entities.CompanyEntity;
import net.nvcm.entities.EmployeeEntity;
import net.nvcm.service.interfaces.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static net.nvcm.service.CompanyTransformer.transformCompanyDTOToEntity;
import static net.nvcm.service.CompanyTransformer.transformCompanyEntityToDTO;

/**
 * Created by byaxe on 4/8/16.
 */
@Service
public class CompanyServiceImpl implements ICompanyService {

    @Autowired
    private ICompanyDAO companyDAO;

    @Autowired
    private IEmployeeDAO employeeDAO;

    @Override
    public List<CompanyDTOFull> getCompaniesList() {
        List<CompanyDTOFull> transformedList = new ArrayList<>();

        for (CompanyEntity company : companyDAO.getCompaniesList()) {
            transformedList.add(transformCompanyEntityToDTO(company));
        }

        return transformedList;
    }

    @Override
    public CompanyDTOFull getCompanyById(final int id) {
        return transformCompanyEntityToDTO(companyDAO.getCompanyById(id));
    }

    @Override
    public List<CompanyDTOFull> getCompaniesListById(final int id) {
        EmployeeEntity targetEmployee = employeeDAO.getEmployeeById(id);

        List<CompanyDTOFull> companyDTOList = new ArrayList<>();

        for (CompanyEntity company : targetEmployee.getCompanies()) {
            companyDTOList.add(transformCompanyEntityToDTO(company));
        }
        return companyDTOList;
    }

    @Override
    public CompanyDTOFull saveCompany(final CompanyDTOFull company) {
        companyDAO.saveCompany(transformCompanyDTOToEntity(company));
        return company;
    }

    @Override
    public CompanyEntity saveCompanyToEmployee(final int employee_id, final CompanyDTOFull company) {
        return null;
    }

    @Override
    public CompanyDTOFull removeCompany(final int id) {
        companyDAO.removeCompany(id);

        return getCompanyById(id);
    }

    @Override
    public boolean isCompanyExist(final CompanyDTOFull company) {
        return companyDAO.isCompanyExist(transformCompanyDTOToEntity(company));
    }
}