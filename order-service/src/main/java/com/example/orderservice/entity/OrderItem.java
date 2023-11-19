package com.example.orderservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_items")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class OrderItem extends BaseEntity{

    private String name;
    private double unitPrice;

    private int qty;

    private Long productId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public OrderItem(String name, double unitPrice, int qty, Long productId, Order order){
        this.name = name;
        this.unitPrice = unitPrice;
        this.qty = qty;
        this.productId = productId;
        this.order = order;
    }

}
