package com.example.productservice.pojo;

import com.example.productservice.entity.Product;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResponseProduct {

    private Long id;
    private String name;
    private int availableQty;
    private double unitPrice;
    private LocalDateTime createdAt;
    private Long userId;


    public ResponseProduct(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.availableQty = product.getAvailableQty();
        this.unitPrice = product.getUnitPrice();
        this.createdAt = product.getCreatedAt();
        this.userId = product.getUserId();
    }

}
