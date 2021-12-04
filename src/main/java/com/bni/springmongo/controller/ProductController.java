package com.bni.springmongo.controller;

import com.bni.springmongo.model.Product;
import com.bni.springmongo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    private final ProductService productService;

    @PostMapping()
    public ResponseEntity<Product> create(@RequestBody Product product){
        Product productCreated = productService.create(product);
        return ResponseEntity.ok(productCreated);
    }
    @GetMapping
    public ResponseEntity<List<Product>> getList(){
        List<Product> products = productService.listProduct();
        return ResponseEntity.ok(products);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getProduct(@PathVariable String id){
        try {
            Product product = productService.findById(id);
            return ResponseEntity.ok(product);
        }catch (Exception e){
            if(e.getMessage().equalsIgnoreCase("Not Found")){
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.internalServerError().build();
        } 
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable String id, @RequestBody Product product){
        try {
            Product productUpdated = productService.update(id,product);
            return ResponseEntity.ok(productUpdated);
        }catch (Exception e){
            if (e.getMessage().equalsIgnoreCase("Not Found")){
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        try {
            productService.delete(id);
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            if (e.getMessage().equalsIgnoreCase("Not Found")){
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.internalServerError().build();
        }

    }

}
