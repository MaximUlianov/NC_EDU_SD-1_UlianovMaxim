package com.netcracker.edu.backend.controller;


import com.netcracker.edu.backend.entity.CompanyInfo;
import com.netcracker.edu.backend.service.CompanyInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/company")
public class CompanyInfoController {

    private CompanyInfoService companyService;

    @Autowired
    public CompanyInfoController(CompanyInfoService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<String> addCompany(@RequestBody CompanyInfo newCompany){
        return ResponseEntity.ok(companyService.addCompany(newCompany));
    }

    @GetMapping(value = "/{page}/{elements}")
    @ResponseBody
    public ResponseEntity<List<CompanyInfo>> getCompanies(@PathVariable(name = "page") int page,
                                                                  @PathVariable(name = "elements") int elements){
        return ResponseEntity.ok(companyService.getCompanies(page, elements));
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<CompanyInfo>> getAllCompanies(){
        return ResponseEntity.ok(companyService.getAllCompanies());
    }

    @DeleteMapping
    @ResponseBody
    public ResponseEntity<String> deleteCompany(@RequestParam(value = "id") long id){
        return ResponseEntity.ok(companyService.deleteCompany(id));
    }

}
