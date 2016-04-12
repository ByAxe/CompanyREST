package net.nvcm.service.implementation;

import net.nvcm.dao.interfaces.ICompanyDAO;
import net.nvcm.entities.CompanyEntity;
import net.nvcm.entities.EmployeeEntity;
import net.nvcm.service.interfaces.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by byaxe on 4/8/16.
 */
@Service
public class CompanyServiceImpl implements ICompanyService {

    @Autowired
    private ICompanyDAO companyDAO;

    @Override
    public List<CompanyEntity> getCompaniesList() {
        return this.companyDAO.getCompaniesList();
    }

    @Override
    public CompanyEntity getCompanyById(final int id) {
        return this.companyDAO.getCompanyById(id);
    }

    @Override
    public List<EmployeeEntity> getEmployeesListById(final int id) {
        return this.companyDAO.getEmployeesListById(id);
    }

    @Override
    public CompanyEntity saveCompany(final CompanyEntity company) {
        return this.companyDAO.saveCompany(company);
    }

    @Override
    public EmployeeEntity saveEmployeeToCompany(final int company_id, final EmployeeEntity employee) {
        return this.companyDAO.saveEmployeeToCompany(company_id, employee);
    }

    @Override
    public CompanyEntity removeCompany(final int id) {
        return this.companyDAO.removeCompany(id);
    }

    @Override
    public boolean isCompanyExist(final CompanyEntity company) {
        return this.companyDAO.isCompanyExist(company);
    }
}
