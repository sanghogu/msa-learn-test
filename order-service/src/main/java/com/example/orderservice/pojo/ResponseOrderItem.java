package com.example.orderservice.pojo;

import com.example.orderservice.entity.OrderItem;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ResponseOrderItem {

    private Long id;
    private String name;


    private double unitPrice;

    private int qty;

    private Long productId;

    public ResponseOrderItem(OrderItem oi) {
        this.id = oi.getId();
        this.name = oi.getName();
        this.unitPrice = oi.getUnitPrice();
        this.qty = oi.getQty();
        this.productId = oi.getProductId();
    }
}
