package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Product;
import com.netcracker.edu.backend.entity.Response;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts(int page, int size);
    List<Product> getProductByPartOfName(String value);

    Response addProduct(Product product);
    Response deleteProduct(long id);
    Response setSale(Product product);

    int getTotalPages(int perPage);
}
