package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.Product;
import com.netcracker.edu.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping(value = "/{page}/{elements}")
    @ResponseBody
    public ResponseEntity getAllSubscriptions(@PathVariable(name = "page") int page,
                                                             @PathVariable(name = "elements") int elements){
        return ResponseEntity.ok(productService.getAllProducts(page, elements));
    }


    @GetMapping(value = "/totalPages")
    @ResponseBody
    public ResponseEntity getTotalPages(@RequestParam(name = "perPage") int perPage){
        return ResponseEntity.ok(productService.getTotalPages(perPage));
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity getSubscrByPartOfName(@RequestParam(value = "value") String value){
        return ResponseEntity.ok(productService.getProductByPartOfName(value));
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity addProduct(@RequestBody Product product){
        return ResponseEntity.ok(productService.addProduct(product));
    }

    @DeleteMapping
    @ResponseBody
    public ResponseEntity deleteSubscription(@RequestParam(value = "id") long id){
        return ResponseEntity.ok(productService.deleteProduct(id));
    }

    @PostMapping(value = "/sale")
    @ResponseBody
    public ResponseEntity setSale(@RequestBody Product product){
        return ResponseEntity.ok(productService.setSale(product));
    }


}
