package com.example.orderservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Order extends BaseEntity{

    @Column(unique = true)
    private String identityName;
    private String buyerName;

    private Long userId;

    @OneToMany(mappedBy = "order")
    @Setter
    private List<OrderItem> orderItems;

    public Order(String identityName, String buyerName, Long userId) {
        this.identityName = identityName;
        this.buyerName = buyerName;
        this.userId = userId;
    }

}
