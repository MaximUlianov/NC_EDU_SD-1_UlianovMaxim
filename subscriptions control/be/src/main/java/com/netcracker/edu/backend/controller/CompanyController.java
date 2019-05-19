package com.netcracker.edu.backend.controller;


import com.netcracker.edu.backend.entity.Company;
import com.netcracker.edu.backend.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    private CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity addCompany(@RequestBody Company newCompany){
        return ResponseEntity.ok(companyService.addCompany(newCompany));
    }

    @GetMapping(value = "totalPages")
    @ResponseBody
    public ResponseEntity getTotalPages(@RequestParam(value = "perPage") int perPage){
        return ResponseEntity.ok(companyService.getTotalPages(perPage));
    }

    @GetMapping(value = "/{page}/{elements}")
    @ResponseBody
    public ResponseEntity getCompanies(@PathVariable(name = "page") int page,
                                                      @PathVariable(name = "elements") int elements){
        return ResponseEntity.ok(companyService.getCompanies(page, elements));
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity getAllCompanies(){
        return ResponseEntity.ok(companyService.getAllCompanies());
    }

    @DeleteMapping
    @ResponseBody
    public ResponseEntity deleteCompany(@RequestParam(value = "id") long id){
        return ResponseEntity.ok(companyService.deleteCompany(id));
    }

    @GetMapping(value = "/products")
    @ResponseBody
    public  ResponseEntity getSubscrByCompany(@RequestParam(name = "id") long id){
        return ResponseEntity.ok(companyService.getProductByCompany(id));
    }

    @PutMapping
    @ResponseBody
    public  ResponseEntity editCompany(@RequestBody Company company){
        return ResponseEntity.ok(companyService.editCompany(company));
    }

}
