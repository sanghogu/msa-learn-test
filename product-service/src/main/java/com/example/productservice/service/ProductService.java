package com.example.productservice.service;

import com.example.productservice.entity.Product;
import com.example.productservice.pojo.RequestProduct;
import com.example.productservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(RequestProduct requestProduct) {

        Product product = new Product(requestProduct.getName(), requestProduct.getUnitPrice(), requestProduct.getAvailableQty(), requestProduct.getUserId());

        return productRepository.save(product);

    }

    public Product findByTitle(String name) {
        return productRepository.findByName(name);
    }

    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }
}
