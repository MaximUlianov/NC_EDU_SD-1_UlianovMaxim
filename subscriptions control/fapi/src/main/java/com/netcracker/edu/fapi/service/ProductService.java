package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.Product;
import com.netcracker.edu.fapi.models.Response;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts(int page, int size);
    List<Product> getProductByPartOfName(String value);

    Response addProduct(Product product);
    Response deleteProduct(long id);
    Response setSale(Product product);

    int getTotalPages(int size);
}
