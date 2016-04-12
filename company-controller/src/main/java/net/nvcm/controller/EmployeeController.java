package net.nvcm.controller;

import net.nvcm.entities.CompanyEntity;
import net.nvcm.entities.EmployeeEntity;
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
@RequestMapping(method = RequestMethod.GET)
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    /**
     * Obtaining single employee by id
     */
    @RequestMapping(value = "/employees/{id}", method = RequestMethod.GET)
    public ResponseEntity<EmployeeEntity> getEmployee(@PathVariable("id") final int id) {
        EmployeeEntity employee = employeeService.getEmployeeById(id);

        if (employee == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    /**
     * Obtaining list of all employees
     */
    @RequestMapping(value = "/employees")
    public ResponseEntity<List<EmployeeEntity>> listAllEmployees() {
        List<EmployeeEntity> employees = employeeService.getEmployeeList();

        if (employees.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(employees, HttpStatus.OK);
    }


    /**
     * Saving employee
     */
    @RequestMapping(value = "/employees/", method = RequestMethod.POST)
    public ResponseEntity<Void> createEmployee(@RequestBody EmployeeEntity employee, UriComponentsBuilder builder) {

        if (employeeService.isEmployeeExist(employee)) return new ResponseEntity<>(HttpStatus.CONFLICT);

        employeeService.saveEmployee(employee);

        HttpHeaders headers = new HttpHeaders();

        headers.setLocation(builder.path("/employees/{id}").buildAndExpand(employee.getId()).toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    /**
     * Removing employee
     */
    @RequestMapping(value = "/employees/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<EmployeeEntity> deleteEmployee(@PathVariable("id") final int id) {
        EmployeeEntity employee = employeeService.getEmployeeById(id);

        if (employee == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        employeeService.removeEmployee(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Obtain company's list of given employee
     */
    @RequestMapping(value = "/employees/{id}/companies", method = RequestMethod.GET)
    public ResponseEntity<List<CompanyEntity>> listAllCompaniesInEmployee(@PathVariable("id") final int id) {
        List<CompanyEntity> companyList = employeeService.getCompaniesListById(id);

        if (companyList.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(companyList, HttpStatus.OK);
    }

    /**
     * Add company to employee
     */
    @RequestMapping(value = "/employees/{employee_id}/companies", method = RequestMethod.PUT)
    public ResponseEntity<CompanyEntity> addCompanyToEmployee(
            @PathVariable("employee_id") final int employee_id,
            @RequestBody CompanyEntity company, UriComponentsBuilder builder) {

        employeeService.saveCompanyToEmployee(employee_id, company);

        HttpHeaders headers = new HttpHeaders();

        headers.setLocation(builder.path("/employees/{employee_id}/companies/{company_id}")
                .buildAndExpand(company.getId()).toUri());

        return new ResponseEntity<>(headers, HttpStatus.OK);
    }

}
