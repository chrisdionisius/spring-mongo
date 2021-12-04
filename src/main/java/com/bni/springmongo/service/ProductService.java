package com.bni.springmongo.service;

import com.bni.springmongo.model.Product;

import java.util.List;

public interface ProductService {
    Product create(Product product);
    List<Product> listProduct();
    Product findById(String id);
    Product update(String id, Product product);
    void delete(String id);
}
