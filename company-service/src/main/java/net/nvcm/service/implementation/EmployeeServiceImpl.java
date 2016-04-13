package net.nvcm.service.implementation;

import net.nvcm.core.dto.EmployeeDTOFull;
import net.nvcm.repository.interfaces.ICompanyRepository;
import net.nvcm.repository.interfaces.IEmployeeRepository;
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
    private ICompanyRepository companyRepository;

    @Autowired
    private IEmployeeRepository employeeRepository;

    @Override
    public List<EmployeeDTOFull> getEmployeeList() {
        List<EmployeeDTOFull> transformedList = new ArrayList<>();

        for (EmployeeEntity employee : employeeRepository.findAll()) {
            EmployeeDTOFull employeeDTO = transformEmployeeEntityToDTO(employee);

            transformedList.add(employeeDTO);
        }

        return transformedList;
    }

    @Override
    public EmployeeDTOFull getEmployeeById(final int id) {
        return transformEmployeeEntityToDTO(employeeRepository.findOne(id));
    }

    @Override
    public List<EmployeeDTOFull> getEmployeesListById(final int id) {
        CompanyEntity targetCompany = companyRepository.findOne(id);

        List<EmployeeDTOFull> employeesDTOList = new ArrayList<>();

        for (EmployeeEntity employee : targetCompany.getEmployees()) {
            EmployeeDTOFull employeeDTO = transformEmployeeEntityToDTO(employee);
            employeesDTOList.add(employeeDTO);
        }

        return employeesDTOList;
    }

    @Override
    public EmployeeDTOFull saveEmployee(final EmployeeDTOFull employee) {
        EmployeeEntity employeeEntity = transformEmployeeDTOToEntity(employee);

        employeeRepository.save(employeeEntity);

        return employee;
    }

    /*TODO implement it*/
    @Override
    public EmployeeDTOFull saveEmployeeToCompany(final int company_id, final EmployeeDTOFull employee) {
        return null;
    }

    @Override
    public EmployeeDTOFull removeEmployee(final int id) {
        employeeRepository.delete(id);

        return getEmployeeById(id);
    }

    @Override
    public boolean isExist(final EmployeeDTOFull employee) {
        EmployeeEntity employeeEntity = transformEmployeeDTOToEntity(employee);

        return employeeRepository.isExist(employeeEntity);
    }
}