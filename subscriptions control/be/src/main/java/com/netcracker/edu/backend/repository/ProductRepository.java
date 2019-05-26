package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.Product;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
    List<Product> findByNameContains(String value);
    Product findByName(String name);
}
