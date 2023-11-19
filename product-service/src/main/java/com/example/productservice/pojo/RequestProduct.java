package com.example.productservice.pojo;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RequestProduct {


    @NotNull(message = "아이템 이름 필수")
    private String name;
    @DecimalMin(value = "0.1", message = "가격은 0.1보다 커야함")
    private double unitPrice;

    @Min(value = 1, message = "가용 재고는 0개 이상이어야함")
    private int availableQty;

    @NotNull(message = "user id 는 있어야함")
    private Long userId;

}
