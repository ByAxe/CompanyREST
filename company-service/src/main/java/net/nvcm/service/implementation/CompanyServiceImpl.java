package net.nvcm.service.implementation;

import com.google.common.base.Optional;
import net.nvcm.core.dto.CompanyDTOFull;
import net.nvcm.entities.CompanyEntity;
import net.nvcm.entities.EmployeeEntity;
import net.nvcm.repository.interfaces.CompanyRepository;
import net.nvcm.repository.interfaces.EmployeeRepository;
import net.nvcm.service.interfaces.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static net.nvcm.service.CompanyTransformer.transformCompanyDTOToEntity;
import static net.nvcm.service.CompanyTransformer.transformCompanyEntityToDTO;

/**
 * Created by byaxe on 4/8/16.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CompanyServiceImpl implements ICompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

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
    public CompanyDTOFull saveCompanyToEmployee(final int employee_id, final int company_id) {
        CompanyEntity companyEntity = companyRepository.saveCompanyToEmployee(employee_id, company_id);

        return transformCompanyEntityToDTO(companyEntity);
    }

    @Override
    public CompanyDTOFull removeCompany(final int id) {
        CompanyDTOFull removedCompany = getCompanyById(id);

        companyRepository.delete(id);

        return removedCompany;
    }

    @Override
    public CompanyDTOFull updateCompany(final int id, final CompanyDTOFull company) {

        CompanyEntity companyEntity = companyRepository.findOne(id);

        Optional<String> title = Optional.fromNullable(company.getTitle());
        Optional<String> slogan = Optional.fromNullable(company.getSlogan());

        if(title.isPresent()) companyEntity.setTitle(title.get());
        if(slogan.isPresent()) companyEntity.setSlogan(slogan.get());

        return company;
    }

    @Override
    public boolean isExist(final CompanyDTOFull company) {
        return companyRepository.isExist(company.getTitle());
    }
}