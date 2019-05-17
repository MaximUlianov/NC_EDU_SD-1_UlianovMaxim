package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.Category;
import com.netcracker.edu.backend.entity.Product;
import com.netcracker.edu.backend.entity.Response;
import com.netcracker.edu.backend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {


    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Category>> getAllCategories(){
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping(value = "/products")
    @ResponseBody
    public  ResponseEntity<List<Product>> getProductsByCategory(@RequestParam(name = "id") long id){
        return ResponseEntity.ok(categoryService.getProductByCategory(id));
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Response> addCategory(@RequestBody Category category){
        return ResponseEntity.ok(categoryService.addCategory(category));
    }
}
