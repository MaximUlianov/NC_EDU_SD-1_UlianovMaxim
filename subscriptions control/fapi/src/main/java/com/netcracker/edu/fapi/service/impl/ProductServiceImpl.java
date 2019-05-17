package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.Product;
import com.netcracker.edu.fapi.models.Response;
import com.netcracker.edu.fapi.service.ProductService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public List<Product> getAllProducts(int page, int size) {
        RestTemplate restTemplate = new RestTemplate();
        Product [] productsResponse = restTemplate.getForObject(backendServerUrl + "/api/product/" + page + "/" + size, Product[].class);
        return productsResponse == null ? Collections.emptyList() : Arrays.asList(productsResponse);
    }

    @Override
    public List<Product> getProductByPartOfName(String value) {
        RestTemplate restTemplate = new RestTemplate();
        Product [] productsResponse = restTemplate.getForObject(backendServerUrl + "/api/product?value=" + value, Product[].class);
        return productsResponse == null ? Collections.emptyList() : Arrays.asList(productsResponse);
    }

    @Override
    public Response addProduct(Product product) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendServerUrl + "/api/product", product, Response.class).getBody();
    }

    @Override
    public Response deleteProduct(long id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl + "/api/product?id=" + id);
        return new Response("ok");
    }

    @Override
    public int getTotalPages(int size) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendServerUrl + "/api/product/totalPages?perPage=" + size, Integer.class);
    }

    @Override
    public Response setSale(Product product) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendServerUrl + "/api/product/sale", product, Response.class).getBody();
    }
}
