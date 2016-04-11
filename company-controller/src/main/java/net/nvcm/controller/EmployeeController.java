package net.nvcm.controller;

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

/*    @Autowired
    private ICompanyService companyService;*/


    /**
     * Obtaining single employee by id
     */
    @RequestMapping(value = "/employee/{id}")
    public ResponseEntity<EmployeeEntity> getEmployee(@PathVariable("id") final int id) {
        EmployeeEntity employee = employeeService.getEmployeeById(id);

        if (employee == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    /**
     * Obtaining list of all employees
     */
    @RequestMapping(value = "/employee")
    public ResponseEntity<List<EmployeeEntity>> listAllEmployees() {
        List<EmployeeEntity> employees = employeeService.getEmployeeList();

        if (employees.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(employees, HttpStatus.OK);
    }


    /**
     * Saving employee
     */
    @RequestMapping(value = "/employee/", method = RequestMethod.POST)
    public ResponseEntity<Void> createEmployee(@RequestBody EmployeeEntity employee, UriComponentsBuilder builder) {

        if (employeeService.isEmployeeExist(employee)) return new ResponseEntity<>(HttpStatus.CONFLICT);

        employeeService.saveEmployee(employee);

        HttpHeaders headers = new HttpHeaders();

        headers.setLocation(builder.path("/employee/{id}").buildAndExpand(employee.getId()).toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    /**
     * Removing employee
     */
    @RequestMapping(value = "/employee/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<EmployeeEntity> deleteEmployee(@PathVariable("id") final int id) {
        EmployeeEntity employee = employeeService.getEmployeeById(id);

        if (employee == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        employeeService.removeEmployee(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
