package com.example.orderservice.pojo;

import com.example.orderservice.entity.Order;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class RequestOrder {

    @NotNull(message = "식별 이름 필수로 적어야함")
    @Size(min = 2, message = "식별 이름 최소 2글자이")
    private String identityName;

    @NotNull(message = "구매자 이름 필수로 적어야함")
    @Size(min = 2, message = "구매자 이름 최소 2글자이")
    private String buyerName;

    @NotNull(message = "유저 아디 필요함")
    private Long userId;

    @Min(value = 1, message = "아이템 최소 1개 이상 필요함")
    private List<RequestOrderItem> orderItems;

}
