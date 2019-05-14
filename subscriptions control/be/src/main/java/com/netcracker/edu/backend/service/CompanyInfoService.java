package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.CompanyInfo;
import com.netcracker.edu.backend.entity.Response;

import java.util.List;

public interface CompanyInfoService {

    List<CompanyInfo> getCompanies(int page, int perPage);
    List<CompanyInfo> getAllCompanies();

    Response addCompany(CompanyInfo newCompany);
    Response deleteCompany(long id);
}
