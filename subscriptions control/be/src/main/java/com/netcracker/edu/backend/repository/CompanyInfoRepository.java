package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.CompanyInfo;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyInfoRepository extends PagingAndSortingRepository<CompanyInfo, Long> {
    CompanyInfo findByName(String name);
}
