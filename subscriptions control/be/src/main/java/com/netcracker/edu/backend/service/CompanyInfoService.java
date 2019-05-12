package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.CompanyInfo;

import java.util.List;

public interface CompanyInfoService {
    String addCompany(CompanyInfo newCompany);
    List<CompanyInfo> getCompanies(int page, int perPage);
    List<CompanyInfo> getAllCompanies();
    String deleteCompany(long id);
}
