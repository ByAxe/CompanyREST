package net.nvcm.controller;

import net.nvcm.entities.CompanyEntity;
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
/*
    @Autowired
    private IEmployeeService employeeService;*/

    @Autowired
    private ICompanyService companyService;


    /**
     * Obtaining single company by id
     */
    @RequestMapping(value = "/company/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<CompanyEntity> getCompany(@PathVariable("id") final int id) {
        CompanyEntity company = companyService.getCompanyById(id);

        if (company == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(company, HttpStatus.OK);
    }


    /**
     * Obtaining list of all companies
     */
    @RequestMapping(value = "/company")
    public ResponseEntity<List<CompanyEntity>> listAllCompanies() {
        List<CompanyEntity> companies = companyService.getCompaniesList();

        if (companies.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(companies, HttpStatus.OK);
    }


    /**
     * Saving company
     */
    @RequestMapping(value = "/company/", method = RequestMethod.POST)
    public ResponseEntity<Void> createCompany(@RequestBody CompanyEntity company, UriComponentsBuilder builder) {
        if (companyService.isCompanyExist(company)) return new ResponseEntity<>(HttpStatus.CONFLICT);

        companyService.saveCompany(company);

        HttpHeaders headers = new HttpHeaders();

        headers.setLocation(builder.path("/company/{id}").buildAndExpand(company.getId()).toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    /**
     * Removing company
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<CompanyEntity> deleteCompany(@PathVariable("id") final int id) {
        CompanyEntity company = companyService.getCompanyById(id);

        if (company == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        companyService.removeCompany(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
