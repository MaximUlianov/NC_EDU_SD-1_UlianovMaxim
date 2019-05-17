package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.Company;
import com.netcracker.edu.fapi.models.Product;
import com.netcracker.edu.fapi.models.Response;
import com.netcracker.edu.fapi.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/company")
public class CompanyController {

    private CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public List<Company> getCompanies(){
        return companyService.getCompanies();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Response addCompany(@RequestBody Company company){
        return companyService.addCompany(company);
    }

    @GetMapping(value = "/{page}/{size}")
    public List<Company> getAllCompanies(@PathVariable(name = "page") int page,
                                           @PathVariable(name = "size") int size){
        return companyService.getAllCompanies(page, size);
    }

    @GetMapping(value = "/products")
    public List<Product> getSubscrByCompany(@RequestParam(name = "id") long id){
        return companyService.getProductsByCompanyId(id);
    }
}
