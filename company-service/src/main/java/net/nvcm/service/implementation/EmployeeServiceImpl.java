package net.nvcm.service.implementation;

import com.google.common.base.Optional;
import net.nvcm.core.dto.EmployeeDTOFull;
import net.nvcm.entities.CompanyEntity;
import net.nvcm.entities.EmployeeEntity;
import net.nvcm.repository.interfaces.CompanyRepository;
import net.nvcm.repository.interfaces.EmployeeRepository;
import net.nvcm.service.interfaces.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static net.nvcm.service.EmployeeTransformer.transformEmployeeDTOToEntity;
import static net.nvcm.service.EmployeeTransformer.transformEmployeeEntityToDTO;

/**
 * Created by byaxe on 4/8/16.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

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
        EmployeeDTOFull employeeDTO = null;

        try {
            employeeDTO = transformEmployeeEntityToDTO(employeeRepository.findOne(id));
        } catch (NullPointerException ignored) {
        }

        return employeeDTO;
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

    @Override
    public EmployeeDTOFull saveEmployeeToCompany(final int company_id, final int employee_id) {
        EmployeeEntity employeeEntity = employeeRepository.saveEmployeeToCompany(company_id, employee_id);

        return transformEmployeeEntityToDTO(employeeEntity);
    }

    @Override
    public EmployeeDTOFull removeEmployee(final int id) {
        EmployeeDTOFull removedEmployee = getEmployeeById(id);

        employeeRepository.delete(id);

        return removedEmployee;
    }

    @Override
    public EmployeeDTOFull updateEmployee(final int id, final EmployeeDTOFull employee) {

        EmployeeEntity employeeEntity = employeeRepository.findOne(id);

        Optional<String> name = Optional.fromNullable(employee.getName());
        Optional<String> position = Optional.fromNullable(employee.getPosition());
        Optional<String> sex = Optional.fromNullable(employee.getSex());
        Optional<Integer> age = Optional.fromNullable(employee.getAge());

        if (name.isPresent()) employeeEntity.setName(name.get());
        if (position.isPresent()) employeeEntity.setPosition(position.get());
        if (sex.isPresent()) employeeEntity.setSex(sex.get());
        if (age.isPresent()) employeeEntity.setAge(age.get());

        return employee;
    }

    @Override
    public boolean isExist(final EmployeeDTOFull employee) {
        EmployeeEntity employeeEntity = transformEmployeeDTOToEntity(employee);

        return employeeRepository.isExist(employeeEntity);
    }
}