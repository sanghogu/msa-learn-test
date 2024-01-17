package com.example.productservice.pojo;

import jakarta.annotation.Nullable;
import lombok.Getter;

@Getter
public class RequestUpdateProduct {
    @Nullable
    private Integer plusQty;
    @Nullable
    private String name;
}
