package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.Company;
import com.netcracker.edu.fapi.models.Response;
import com.netcracker.edu.fapi.service.CompanyService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public List<Company> getCompanies() {
        RestTemplate restTemplate = new RestTemplate();
        Company [] companyResponse = restTemplate.getForObject(backendServerUrl + "/api/company", Company[].class);
        return companyResponse == null ? Collections.emptyList() : Arrays.asList(companyResponse);
    }

    @Override
    public Response addCompany(Company company) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendServerUrl + "/api/company", company, Response.class).getBody();
    }

    @Override
    public List<Company> getAllCompanies(int page, int perPage) {
        RestTemplate restTemplate = new RestTemplate();
        Company [] companyResponse = restTemplate.getForObject(backendServerUrl + "/api/company/" + page + "/" + perPage, Company[].class);
        return companyResponse == null ? Collections.emptyList() : Arrays.asList(companyResponse);
    }
}
