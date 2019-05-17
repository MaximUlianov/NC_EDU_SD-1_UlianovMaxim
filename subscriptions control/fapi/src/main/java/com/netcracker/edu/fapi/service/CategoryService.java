package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.Category;
import com.netcracker.edu.fapi.models.Product;
import com.netcracker.edu.fapi.models.Response;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();
    List<Product> getProductByCategoryId(long id);
    Response addCategory(Category category);

}
