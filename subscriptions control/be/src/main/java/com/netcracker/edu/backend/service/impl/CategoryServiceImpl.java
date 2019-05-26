package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.Category;
import com.netcracker.edu.backend.entity.Product;
import com.netcracker.edu.backend.entity.Response;
import com.netcracker.edu.backend.repository.CategoryRepository;
import com.netcracker.edu.backend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategories() {
        return (List<Category>) categoryRepository.findAll();
    }

    @Override
    public List<Product> getProductByCategory(long id) {
        Category category = categoryRepository.findById(id).get();
        return new ArrayList<>(category.getProducts());
    }

    @Override
    public Response addCategory(Category category) {
        Category temp = categoryRepository.findByName(category.getName());
        if(temp == null) {
            categoryRepository.save(category);
            return new Response("ok");
        }
        else{
            return new Response("exists");
        }
    }

    @Override
    public Category getByName(String name) {
        return categoryRepository.findByName(name);
    }
}
