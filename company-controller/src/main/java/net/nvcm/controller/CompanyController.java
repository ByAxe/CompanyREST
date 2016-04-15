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
public class CompanyController {

    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private ICompanyService companyService;


    /**
     * Obtaining single company by id
     */
    @RequestMapping(value = "/companies/{id}", method = RequestMethod.GET)
    public ResponseEntity<CompanyDTOFull> getCompany(@PathVariable("id") final int id) {
        CompanyDTOFull company = companyService.getCompanyById(id);

        if (company == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(company, HttpStatus.OK);
    }


    /**
     * Obtaining list of all companies
     */
    @RequestMapping(value = "/companies", method = RequestMethod.GET)
    public ResponseEntity<List<CompanyDTOFull>> listAllCompanies() {
        List<CompanyDTOFull> companies = companyService.getCompaniesList();

        if (companies.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(companies, HttpStatus.OK);
    }


    /**
     * Saving company
     */
    @RequestMapping(value = "/companies/", method = RequestMethod.POST)
    public ResponseEntity<Void> createCompany(@RequestBody CompanyDTOFull company, UriComponentsBuilder builder) {
        if (companyService.isExist(company)) return new ResponseEntity<>(HttpStatus.CONFLICT);

        companyService.saveCompany(company);

        HttpHeaders headers = new HttpHeaders();

        headers.setLocation(builder.path("/companies/{id}").buildAndExpand(company.getId()).toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    /**
     * Removing company
     */
    @RequestMapping(value = "/companies/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<CompanyDTOFull> deleteCompany(@PathVariable("id") final int id) {
        CompanyDTOFull company = companyService.getCompanyById(id);

        if (company == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        companyService.removeCompany(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Obtaining employee's list of given company
     */
    @RequestMapping(value = "/companies/{id}/employees", method = RequestMethod.GET)
    public ResponseEntity<List<EmployeeDTOFull>> listAllEmployeesInCompany(@PathVariable("id") final int id) {
        List<EmployeeDTOFull> employeeList = employeeService.getEmployeesListById(id);

        if (employeeList.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }


    /**
     * Adding employee to company
     */
    @RequestMapping(value = "/companies/{company_id}/employees/{employee_id}", method = RequestMethod.PUT)
    public ResponseEntity<EmployeeDTOFull> addEmployeeToCompany(
            @PathVariable("company_id") final int company_id,
            @PathVariable("employee_id") int employee_id, UriComponentsBuilder builder) {

        employeeService.saveEmployeeToCompany(company_id, employee_id);

        HttpHeaders headers = new HttpHeaders();

        headers.setLocation(builder.path("/companies/{company_id}/employees/{employee_id}")
                .buildAndExpand(company_id, employee_id).toUri());

        return new ResponseEntity<>(headers, HttpStatus.OK);
    }

    /**
     * Update data about company
     */
    @RequestMapping(value = "/companies/{id}", method = RequestMethod.PUT)
    public ResponseEntity<CompanyDTOFull> updateCompany(@PathVariable("id") final int id,
                                                        @RequestBody CompanyDTOFull company) {
        Optional<CompanyDTOFull> companyDTO = Optional.fromNullable(companyService.getCompanyById(id));

        if (!companyDTO.isPresent()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        companyService.updateCompany(id, company);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
