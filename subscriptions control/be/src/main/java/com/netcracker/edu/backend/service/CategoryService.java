package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Category;
import com.netcracker.edu.backend.entity.Product;
import com.netcracker.edu.backend.entity.Response;

import java.util.List;

public interface CategoryService {

    List<Category> getAllCategories();
    List<Product> getProductByCategory(long id);

    Category getByName(String name);

    Response addCategory(Category category);
}
