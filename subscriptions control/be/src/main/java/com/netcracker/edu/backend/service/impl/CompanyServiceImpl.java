package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.Company;
import com.netcracker.edu.backend.entity.Product;
import com.netcracker.edu.backend.entity.Response;
import com.netcracker.edu.backend.repository.CompanyRepository;
import com.netcracker.edu.backend.service.CompanyService;
import com.netcracker.edu.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    private CompanyRepository companyRepository;
    private ProductService productService;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository, ProductService productService) {
        this.companyRepository = companyRepository;
        this.productService = productService;
    }




    @Override
    public Response addCompany(Company newCompany) {
        Company temp = companyRepository.findByName(newCompany.getName());
        if(temp == null) {
            this.companyRepository.save(newCompany);
            return new Response("Saved");
        }
        else{
            return new Response("exists");
        }
    }

    @Override
    public Integer getTotalPages(int perPage) {
        List<Company> list = (List<Company>) companyRepository.findAll();
        if(list.size() == 0){
            return 0;
        }
        else if(list.size()%perPage == 0){
            return (list.size()/perPage);
        }
        else{
            return (list.size()/perPage) + 1;
        }
    }

    @Override
    public List<Company> getCompanies(int page, int perPage) {
        PageRequest pageRequest = PageRequest.of(page - 1, perPage);
        Page<Company> list = companyRepository.findAll(pageRequest);
        return list.getContent();
    }

    @Override
    public List<Company> getAllCompanies() {
        return (List<Company>) companyRepository.findAll();
    }

    @Override
    public Response deleteCompany(long id){
        companyRepository.findById(id).get().getProducts().forEach(product->{
            productService.deleteProduct(product.getId());
        });
        this.companyRepository.deleteById(id);
        return new Response("Deleted");
    }

    @Override
    public List<Product> getProductByCompany(long id) {
        Company company = companyRepository.findById(id).get();
        return new ArrayList<>(company.getProducts());
    }

    @Override
    public Company getByName(String name) {
        return companyRepository.findByName(name);
    }

    @Override
    public Response editCompany(Company company) {
        Company _company = companyRepository.findById(company.getId()).get();
        _company.setName(company.getName());
        _company.setDescription(company.getDescription());
        companyRepository.save(_company);
        return new Response("ok");
    }
}
