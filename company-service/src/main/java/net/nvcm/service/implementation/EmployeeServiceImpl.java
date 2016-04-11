package net.nvcm.service.implementation;

import net.nvcm.dao.interfaces.IEmployeeDAO;
import net.nvcm.entities.CompanyEntity;
import net.nvcm.entities.EmployeeEntity;
import net.nvcm.service.interfaces.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by byaxe on 4/8/16.
 */
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private IEmployeeDAO employeeDAO;

    @Override
    public EmployeeEntity getEmployeeById(final int id) {
        return this.employeeDAO.getEmployeeById(id);
    }

    @Override
    public List<EmployeeEntity> getEmployeeList() {
        return this.employeeDAO.getEmployeeList();
    }

    @Override
    public EmployeeEntity saveEmployee(final EmployeeEntity employee) {
        return this.employeeDAO.saveEmployee(employee);
    }

    @Override
    public CompanyEntity saveCompany(final CompanyEntity company, final EmployeeEntity employee) {
        return this.employeeDAO.saveCompany(company, employee);
    }

    @Override
    public EmployeeEntity removeEmployee(final int id) {
        return this.employeeDAO.removeEmployee(id);
    }

    @Override
    public boolean isEmployeeExist(final EmployeeEntity employee) {
        return this.employeeDAO.isEmployeeExist(employee);
    }
}
