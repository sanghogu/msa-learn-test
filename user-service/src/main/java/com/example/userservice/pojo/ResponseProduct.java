package com.example.userservice.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResponseProduct {

    private String productId;
    private int qty;
    private double unitPrice;
    private double totalPrice;
    private LocalDateTime createdAt;
    private String orderId;

}
