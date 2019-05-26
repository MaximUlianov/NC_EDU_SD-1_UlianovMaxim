package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.Product;
import com.netcracker.edu.backend.entity.Response;
import com.netcracker.edu.backend.repository.CompanyRepository;
import com.netcracker.edu.backend.repository.ProductRepository;
import com.netcracker.edu.backend.service.CategoryService;
import com.netcracker.edu.backend.service.ProductService;
import com.netcracker.edu.backend.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private CompanyRepository companyRepository;
    private CategoryService categoryService;
    private SubscriptionService subscriptionService;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CompanyRepository companyRepository, CategoryService categoryService, SubscriptionService subscriptionService) {
        this.productRepository = productRepository;
        this.companyRepository = companyRepository;
        this.categoryService = categoryService;
        this.subscriptionService = subscriptionService;
    }




    @Override
    public List<Product> getProductByPartOfName(String value) {
        return productRepository.findByNameContains(value);
    }

    @Override
    public int getTotalPages(int perPage) {
        List<Product> list = (List<Product>) productRepository.findAll();
        if(list.size() == 0){
            return 0;
        }
        else if(list.size()%perPage == 0){
            return (list.size()/perPage);
        }
        else{
            return (list.size()/perPage) + 1;
        }
    }

    @Override
    public Response addProduct(Product product) {
        Product temp = productRepository.findByName(product.getName());
        if(temp == null) {
            Product _product = new Product();
            _product.setName(product.getName());
            _product.setDescription(product.getDescription());
            _product.setCostPerMonth(product.getCostPerMonth());
            _product.setCategory(categoryService.getByName(product.getCategory().getName()));
            _product.setCompany(companyRepository.findByName(product.getCompany().getName()));
            productRepository.save(_product);
            return new Response("ok");
        }
        else{
            return new Response("exists");
        }
    }

    @Override
    public Response deleteProduct(long id) {
        Product product = productRepository.findById(id).get();
        product.getSubscriptions().forEach(subscription -> {
            subscriptionService.deleteSubscriptionsByCompany(subscription.getId());
        });
        productRepository.deleteById(id);
        return new Response("Deleted");
    }

    @Override
    public List<Product> getAllProducts(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        Page<Product> list = productRepository.findAll(pageRequest);
        return list.getContent();
    }

    @Override
    public Response setSale(Product product) {
        Product _product = productRepository.findById(product.getId()).get();
        _product.setSale(product.getSale());
        _product.getSubscriptions().forEach(value->{
            subscriptionService.setSale(value.getId(), _product.getSale());
        });
        productRepository.save(_product);
        return new Response("ok");
    }
}
