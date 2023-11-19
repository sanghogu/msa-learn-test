package com.example.productservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "products")
public class Product extends BaseEntity {

    @Column(unique = true)
    private String name;
    private double unitPrice;

    private int availableQty;

    private Long userId;


    public Product(String name, double unitPrice, int availableQty, Long userId) {
        this.name = name;
        this.unitPrice = unitPrice;
        this.availableQty = availableQty;
        this.userId = userId;
    }
}
