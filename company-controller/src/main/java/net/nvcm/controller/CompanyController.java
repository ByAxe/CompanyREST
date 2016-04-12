package net.nvcm.controller;

import net.nvcm.entities.CompanyEntity;
import net.nvcm.entities.EmployeeEntity;
import net.nvcm.service.interfaces.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * Created by byaxe on 4/8/16.
 */
@RestController
@RequestMapping(method = RequestMethod.GET)
public class CompanyController {

    @Autowired
    private ICompanyService companyService;


    /**
     * Obtaining single company by id
     */
    @RequestMapping(value = "/companies/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<CompanyEntity> getCompany(@PathVariable("id") final int id) {
        CompanyEntity company = companyService.getCompanyById(id);

        if (company == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(company, HttpStatus.OK);
    }


    /**
     * Obtaining list of all companies
     */
    @RequestMapping(value = "/companies")
    public ResponseEntity<List<CompanyEntity>> listAllCompanies() {
        List<CompanyEntity> companies = companyService.getCompaniesList();

        if (companies.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(companies, HttpStatus.OK);
    }


    /**
     * Saving company
     */
    @RequestMapping(value = "/companies/", method = RequestMethod.POST)
    public ResponseEntity<Void> createCompany(@RequestBody CompanyEntity company, UriComponentsBuilder builder) {
        if (companyService.isCompanyExist(company)) return new ResponseEntity<>(HttpStatus.CONFLICT);

        companyService.saveCompany(company);

        HttpHeaders headers = new HttpHeaders();

        headers.setLocation(builder.path("/companies/{id}").buildAndExpand(company.getId()).toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    /**
     * Removing company
     */
    @RequestMapping(value = "/companies/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<CompanyEntity> deleteCompany(@PathVariable("id") final int id) {
        CompanyEntity company = companyService.getCompanyById(id);

        if (company == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        companyService.removeCompany(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Obtain employee's list of given company
     */
    @RequestMapping(value = "/companies/{id}/employees")
    public ResponseEntity<List<EmployeeEntity>> listAllEmployeesInCompany(@PathVariable("id") final int id) {
        List<EmployeeEntity> employeeList = companyService.getEmployeesListById(id);

        if (employeeList.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }


    /**
     * Add employee to company
     */
    @RequestMapping(value = "/companies/{company_id}/employees", method = RequestMethod.PUT)
    public ResponseEntity<EmployeeEntity> addEmployeeToCompany(
            @PathVariable("company_id") final int company_id,
            @RequestBody EmployeeEntity employee, UriComponentsBuilder builder) {

        companyService.saveEmployeeToCompany(company_id, employee);

        HttpHeaders headers = new HttpHeaders();

        headers.setLocation(builder.path("/companies/{companies_id}/employees/{employees_id}")
                .buildAndExpand(employee.getId()).toUri());

        return new ResponseEntity<>(headers, HttpStatus.OK);
    }

}
