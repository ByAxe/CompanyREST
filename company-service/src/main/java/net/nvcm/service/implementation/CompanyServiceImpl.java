package net.nvcm.service.implementation;

import net.nvcm.core.dto.CompanyDTOFull;
import net.nvcm.entities.CompanyEntity;
import net.nvcm.entities.EmployeeEntity;
import net.nvcm.repository.interfaces.ICompanyRepository;
import net.nvcm.repository.interfaces.IEmployeeRepository;
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
    private ICompanyRepository companyRepository;

    @Autowired
    private IEmployeeRepository employeeRepository;

    @Override
    public List<CompanyDTOFull> getCompaniesList() {
        List<CompanyDTOFull> transformedList = new ArrayList<>();

        for (CompanyEntity company : companyRepository.findAll()) {
            CompanyDTOFull companyDTO = transformCompanyEntityToDTO(company);

            transformedList.add(companyDTO);
        }

        return transformedList;
    }

    @Override
    public CompanyDTOFull getCompanyById(final int id) {
        return transformCompanyEntityToDTO(companyRepository.findOne(id));
    }

    @Override
    public List<CompanyDTOFull> getCompaniesListById(final int id) {
        EmployeeEntity targetEmployee = employeeRepository.findOne(id);

        List<CompanyDTOFull> companyDTOList = new ArrayList<>();

        for (CompanyEntity company : targetEmployee.getCompanies()) {
            CompanyDTOFull companyDTO = transformCompanyEntityToDTO(company);

            companyDTOList.add(companyDTO);
        }
        return companyDTOList;
    }

    @Override
    public CompanyDTOFull saveCompany(final CompanyDTOFull company) {
        CompanyEntity companyEntity = transformCompanyDTOToEntity(company);
        companyRepository.save(companyEntity);
        return company;
    }

    /*TODO implement it*/
    @Override
    public CompanyEntity saveCompanyToEmployee(final int employee_id, final CompanyDTOFull company) {
        return null;
    }

    @Override
    public CompanyDTOFull removeCompany(final int id) {
        companyRepository.delete(id);

        return getCompanyById(id);
    }

    @Override
    public boolean isExist(final CompanyDTOFull company) {
        return companyRepository.isExist(company.getTitle());
    }
}