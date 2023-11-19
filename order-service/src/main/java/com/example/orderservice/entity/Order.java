package com.example.orderservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Order extends BaseEntity{

    @Column(unique = true)
    private String identityName;
    private String buyerName;

    private Long userId;

    public Order(String identityName, String buyerName, Long userId) {
        this.identityName = identityName;
        this.buyerName = buyerName;
        this.userId = userId;
    }

}
