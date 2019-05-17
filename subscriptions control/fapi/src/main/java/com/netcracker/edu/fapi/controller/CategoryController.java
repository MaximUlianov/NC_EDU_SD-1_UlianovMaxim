package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.Category;
import com.netcracker.edu.fapi.models.Product;
import com.netcracker.edu.fapi.models.Response;
import com.netcracker.edu.fapi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/category")
public class CategoryController {


    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @GetMapping(value = "/products")
    public List<Product> getSubscrByCategory(@RequestParam(name = "id") long id){
        return categoryService.getProductByCategoryId(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Response addCategory(@RequestBody Category category){
        return categoryService.addCategory(category);
    }

}
