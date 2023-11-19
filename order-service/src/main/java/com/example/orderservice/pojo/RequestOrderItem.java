package com.example.orderservice.pojo;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class RequestOrderItem {
    @NotNull(message = "아템 이름 필수로 적어야함")
    @Size(min = 2, message = "아템 이름 최소 2글자이")
    private String name;

    @DecimalMin(value = "0.1", message = "가격은 0.1보다 커야함")
    private double unitPrice;

    @Min(value = 1, message = "재고는 0개 이상이어야함")
    private int qty;

    @NotNull(message = "상품 아이디 필요함")
    private Long productId;
}
