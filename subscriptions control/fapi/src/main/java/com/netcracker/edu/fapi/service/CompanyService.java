package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.Company;
import com.netcracker.edu.fapi.models.Response;

import java.util.List;

public interface CompanyService {
    List<Company> getCompanies();
    List<Company> getAllCompanies(int page, int perPage);
    Response addCompany(Company company);
}
