package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.Company;
import com.netcracker.edu.fapi.models.Product;
import com.netcracker.edu.fapi.models.Response;

import java.util.List;

public interface CompanyService {
    List<Company> getCompanies();
    List<Company> getAllCompanies(int page, int perPage);
    List<Product> getProductsByCompanyId(long id);

    Integer getTotalPages(int perPage);

    Response deleteCompany(long id);
    Response editCompany(Company company);
    Response addCompany(Company company);

}
