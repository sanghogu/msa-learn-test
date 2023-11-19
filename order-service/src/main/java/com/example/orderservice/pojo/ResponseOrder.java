package com.example.orderservice.pojo;

import com.example.orderservice.entity.Order;
import com.example.orderservice.entity.OrderItem;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ResponseOrder {

    private Long id;
    private String identityName;

    private String buyerName;

    private Long userId;

    private List<ResponseOrderItem> orderItems;

    public ResponseOrder(Order order, List<OrderItem> orderItems) {
        this.id = order.getId();
        this.identityName = order.getIdentityName();
        this.buyerName = order.getBuyerName();
        this.userId = order.getUserId();

        this.orderItems = orderItems
                .stream()
                .map(ResponseOrderItem::new)
                .toList();
    }

}
