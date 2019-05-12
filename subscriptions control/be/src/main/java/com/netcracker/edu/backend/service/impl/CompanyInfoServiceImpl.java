package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.CompanyInfo;
import com.netcracker.edu.backend.repository.CompanyInfoRepository;
import com.netcracker.edu.backend.service.CompanyInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyInfoServiceImpl implements CompanyInfoService {

    private CompanyInfoRepository companyRepository;

    @Autowired
    public CompanyInfoServiceImpl(CompanyInfoRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public String addCompany(CompanyInfo newCompany) {
        this.companyRepository.save(newCompany);
        return "Saved";
    }

    @Override
    public List<CompanyInfo> getCompanies(int page, int perPage) {
        PageRequest pageRequest = PageRequest.of(page - 1, perPage);
        Page<CompanyInfo> list = companyRepository.findAll(pageRequest);
        return list.getContent();
    }

    @Override
    public List<CompanyInfo> getAllCompanies() {
        return (List<CompanyInfo>) companyRepository.findAll();
    }

    @Override
    public String deleteCompany(long id){
        this.companyRepository.deleteById(id);
        return "Deleted";
    }
}
