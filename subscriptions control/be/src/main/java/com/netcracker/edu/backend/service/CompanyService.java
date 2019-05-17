package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Company;
import com.netcracker.edu.backend.entity.Product;
import com.netcracker.edu.backend.entity.Response;

import java.util.List;

public interface CompanyService {

    List<Company> getCompanies(int page, int perPage);
    List<Company> getAllCompanies();
    List<Product> getProductByCompany(long id);

    Company getByName(String name);

    Response addCompany(Company newCompany);
    Response deleteCompany(long id);
}
