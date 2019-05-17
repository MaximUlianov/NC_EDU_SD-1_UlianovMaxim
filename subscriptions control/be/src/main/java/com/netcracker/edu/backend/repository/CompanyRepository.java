package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.Company;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends PagingAndSortingRepository<Company, Long> {
    Company findByName(String name);
}
