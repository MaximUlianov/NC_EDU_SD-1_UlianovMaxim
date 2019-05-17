package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.Category;
import com.netcracker.edu.fapi.models.Product;
import com.netcracker.edu.fapi.models.Response;
import com.netcracker.edu.fapi.service.CategoryService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public List<Category> getAllCategories() {
        RestTemplate restTemplate = new RestTemplate();
        Category [] categoriesResponse = restTemplate.getForObject(backendServerUrl + "/api/category", Category[].class);
        return categoriesResponse == null ? Collections.emptyList() : Arrays.asList(categoriesResponse);
    }

    @Override
    public List<Product> getProductByCategoryId(long id) {
        RestTemplate restTemplate = new RestTemplate();
        Product [] productsResponse = restTemplate.getForObject(backendServerUrl + "/api/category/products?id=" + id, Product[].class);
        return productsResponse == null ? Collections.emptyList() : Arrays.asList(productsResponse);
    }

    @Override
    public Response addCategory(Category category) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendServerUrl + "/api/category", category, Response.class).getBody();
    }
}
