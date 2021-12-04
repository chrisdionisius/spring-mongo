package com.bni.springmongo.service.impl;

import com.bni.springmongo.model.Product;
import com.bni.springmongo.repository.ProductRepository;
import com.bni.springmongo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> listProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(String id) {
        return productRepository.findById(id).orElseThrow(()->{
            throw new RuntimeException("Not Found");
        });
    }

    @Override
    public Product update(String id, Product product) {
        Product productUpdated = this.findById(id);
        productUpdated.setName(product.getName());
        productUpdated.setPrice(product.getPrice());
        productUpdated.setQty(product.getQty());
        return productRepository.save(productUpdated);
    }

    @Override
    public void delete(String id) {
        Product product = this.findById(id);
        productRepository.delete(product);
    }
}
