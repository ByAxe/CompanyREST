package net.nvcm.controller;

import com.google.common.base.Optional;
import net.nvcm.core.dto.CompanyDTOFull;
import net.nvcm.core.dto.EmployeeDTOFull;
import net.nvcm.service.interfaces.ICompanyService;
import net.nvcm.service.interfaces.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * Created by byaxe on 4/8/16.
 */
@RestController
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private ICompanyService companyService;

    /**
     * Obtaining single employee by id
     */
    @RequestMapping(value = "/employees/{id}", method = RequestMethod.GET)
    public ResponseEntity<EmployeeDTOFull> getEmployee(@PathVariable("id") final int id) {
        EmployeeDTOFull employee = employeeService.getEmployeeById(id);

        if (employee == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    /**
     * Obtaining list of all employees
     */
    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public ResponseEntity<List<EmployeeDTOFull>> listAllEmployees() {
        List<EmployeeDTOFull> employees = employeeService.getEmployeeList();

        if (employees.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(employees, HttpStatus.OK);
    }


    /**
     * Saving employee
     */
    @RequestMapping(value = "/employees/", method = RequestMethod.POST)
    public ResponseEntity<Void> createEmployee(@RequestBody EmployeeDTOFull employee, UriComponentsBuilder builder) {

        if (employeeService.isExist(employee)) return new ResponseEntity<>(HttpStatus.CONFLICT);

        employeeService.saveEmployee(employee);

        HttpHeaders headers = new HttpHeaders();

        headers.setLocation(builder.path("/employees/{id}").buildAndExpand(employee.getId()).toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    /**
     * Removing employee
     */
    @RequestMapping(value = "/employees/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<EmployeeDTOFull> deleteEmployee(@PathVariable("id") final int id) {
        EmployeeDTOFull employee = employeeService.getEmployeeById(id);

        if (employee == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        employeeService.removeEmployee(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Obtain company's list of given employee
     */
    @RequestMapping(value = "/employees/{id}/companies", method = RequestMethod.GET)
    public ResponseEntity<List<CompanyDTOFull>> listAllCompaniesInEmployee(@PathVariable("id") final int id) {
        List<CompanyDTOFull> companyList = companyService.getCompaniesListById(id);

        if (companyList.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(companyList, HttpStatus.OK);
    }

    /**
     * Add company to employee
     */
    @RequestMapping(value = "/employees/{employee_id}/companies{company_id}", method = RequestMethod.PUT)
    public ResponseEntity<CompanyDTOFull> addCompanyToEmployee(
            @PathVariable("employee_id") final int employee_id,
            @PathVariable("company_id") final int company_id, UriComponentsBuilder builder) {

        companyService.saveCompanyToEmployee(employee_id, company_id);

        HttpHeaders headers = new HttpHeaders();

        headers.setLocation(builder.path("/employees/{employee_id}/companies/{company_id}")
                .buildAndExpand(employee_id, company_id).toUri());

        return new ResponseEntity<>(headers, HttpStatus.OK);
    }

    /**
     * Update data about employee
     */
    @RequestMapping(value = "/employees/{id}", method = RequestMethod.PUT)
    public ResponseEntity<EmployeeDTOFull> updateEmployee(@PathVariable("id") final int id,
                                                          @RequestBody EmployeeDTOFull employee) {
        Optional<EmployeeDTOFull> employeeDTO = Optional.fromNullable(employeeService.getEmployeeById(id));

        if (!employeeDTO.isPresent()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        employeeService.updateEmployee(id, employee);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
