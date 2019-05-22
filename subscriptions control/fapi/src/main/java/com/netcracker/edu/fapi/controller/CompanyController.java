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

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/admin/totalPages")
    public Integer getTotalPages(@RequestParam(value = "perPage") int perPage){
        return companyService.getTotalPages(perPage);
    }

    @GetMapping(value = "/admin/{page}/{perPage}")
    public List<Company> getAllCompanies(@PathVariable(name = "page") int page,
                                           @PathVariable(name = "perPage") int perPage){
        return companyService.getAllCompanies(page, perPage);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping
    public Response deleteCompany(@RequestParam(value = "id") int id){
        return companyService.deleteCompany(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    public Response editCompany(@RequestBody Company company){
        return companyService.editCompany(company);
    }


    @GetMapping(value = "/products")
    public List<Product> getProdByCompany(@RequestParam(name = "id") long id){
        return companyService.getProductsByCompanyId(id);
    }
}
