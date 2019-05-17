package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.Company;
import com.netcracker.edu.backend.entity.Product;
import com.netcracker.edu.backend.entity.Response;
import com.netcracker.edu.backend.repository.CompanyRepository;
import com.netcracker.edu.backend.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    private CompanyRepository companyRepository;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public Response addCompany(Company newCompany) {
        this.companyRepository.save(newCompany);
        return new Response("Saved");
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
}
