package net.nvcm.service.implementation;

import net.nvcm.core.dto.EmployeeDTOFull;
import net.nvcm.dao.interfaces.ICompanyDAO;
import net.nvcm.dao.interfaces.IEmployeeDAO;
import net.nvcm.entities.CompanyEntity;
import net.nvcm.entities.EmployeeEntity;
import net.nvcm.service.interfaces.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static net.nvcm.service.EmployeeTransformer.transformEmployeeDTOToEntity;
import static net.nvcm.service.EmployeeTransformer.transformEmployeeEntityToDTO;

/**
 * Created by byaxe on 4/8/16.
 */
@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private ICompanyDAO companyDAO;

    @Autowired
    private IEmployeeDAO employeeDAO;

    @Override
    public List<EmployeeDTOFull> getEmployeeList() {
        List<EmployeeDTOFull> transformedList = new ArrayList<>();

        for (EmployeeEntity employee : employeeDAO.getEmployeeList()) {
            transformedList.add(transformEmployeeEntityToDTO(employee));
        }

        return transformedList;
    }

    @Override
    public EmployeeDTOFull getEmployeeById(final int id) {
        return transformEmployeeEntityToDTO(employeeDAO.getEmployeeById(id));
    }

    @Override
    public List<EmployeeDTOFull> getEmployeesListById(final int id) {
        CompanyEntity targetCompany = companyDAO.getCompanyById(id);

        List<EmployeeDTOFull> employeesDTOList = new ArrayList<>();

        for (EmployeeEntity employee : targetCompany.getEmployees()) {
            employeesDTOList.add(transformEmployeeEntityToDTO(employee));
        }

        return employeesDTOList;
    }

    @Override
    public EmployeeDTOFull saveEmployee(final EmployeeDTOFull employee) {
        employeeDAO.saveEmployee(transformEmployeeDTOToEntity(employee));
        return employee;
    }

    @Override
    public EmployeeDTOFull saveEmployeeToCompany(final int company_id, final EmployeeDTOFull employee) {
        return null;
    }

    @Override
    public EmployeeDTOFull removeEmployee(final int id) {
        employeeDAO.removeEmployee(id);

        return getEmployeeById(id);
    }

    @Override
    public boolean isEmployeeExist(final EmployeeDTOFull employee) {
        return employeeDAO.isEmployeeExist(transformEmployeeDTOToEntity(employee));
    }
}