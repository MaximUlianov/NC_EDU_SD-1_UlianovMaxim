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

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return (List<Category>) categoryRepository.findAll();
    }

    @Override
    public List<Product> getProductByCategory(long id) {
        Category category = categoryRepository.findById(id).get();
        return new ArrayList<>(category.getSubscriptions());
    }

    @Override
    public Response addCategory(Category category) {
        categoryRepository.save(category);
        return new Response("ok");
    }

    @Override
    public Category getByName(String name) {
        return categoryRepository.findByName(name);
    }
}
