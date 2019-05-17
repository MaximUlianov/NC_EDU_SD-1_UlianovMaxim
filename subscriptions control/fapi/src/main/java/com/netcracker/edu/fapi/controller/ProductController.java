package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.Product;
import com.netcracker.edu.fapi.models.Response;
import com.netcracker.edu.fapi.security.TokenProvider;
import com.netcracker.edu.fapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/product")
public class ProductController {

    private ProductService productService;

    private TokenProvider tokenUtil;

    @Autowired
    public ProductController(ProductService productService, TokenProvider tokenUtil) {
        this.productService = productService;
        this.tokenUtil = tokenUtil;
    }

    @GetMapping(value = "/{page}/{size}")
    public List<Product> getAllSubscr(@PathVariable(name = "page") int page,
                                      @PathVariable(name = "size") int size){
        return productService.getAllProducts(page, size);
    }

    @GetMapping
    public List<Product> getProductByPartOfName(@RequestParam(value = "value") String value){
        return productService.getProductByPartOfName(value);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Response addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }



    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping
    public Response deleteSubscription(@RequestParam(value = "id") long id){
        return productService.deleteProduct(id);
    }

    @GetMapping(value = "/totalPages")
    public Integer getTotalPages(@RequestParam(value = "perPage") int size){
        return productService.getTotalPages(size);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/sale")
    public Response setSale(@RequestBody Product product){
        return productService.setSale(product);
    }

}
