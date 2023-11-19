package com.example.orderservice.controller;

import com.example.orderservice.entity.Order;
import com.example.orderservice.pojo.RequestOrder;
import com.example.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.StreamSupport;

@RequestMapping("/order-service")
@Controller
public class OrderController {

    private final OrderService orderService;
    private final String greetingMessage;

    public OrderController(OrderService orderService, @Value("${greeting.welcome}") String greetingMessage) {
        this.orderService = orderService;

        this.greetingMessage = greetingMessage;
    }

    @GetMapping("/health")
    @ResponseBody
    public String index(){

        return "OK";
    }

    @PostMapping("/orders")
    @ResponseBody
    public ResponseEntity<RequestOrder> createUser(@RequestBody RequestOrder requestOrder) {

        orderService.createOrder(requestOrder);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(requestOrder);
    }

    @GetMapping("/welcome")
    @ResponseBody
    public String welcome(){
        return greetingMessage;
    }

    @GetMapping("/orders/{identityName}")
    @ResponseBody
    public ResponseEntity<RequestOrder> getUsers(@PathVariable String identityName) {

        Order order = orderService.findByIdentityName(identityName);

        RequestOrder responseUser = order == null ? null : new RequestOrder(order);

        return ResponseEntity.ok(responseUser);
    }

    @GetMapping("/orders")
    @ResponseBody
    public ResponseEntity<List<RequestOrder>> getUsers(){

        Iterable<Order> iterable = orderService.findAll();

        List<RequestOrder> responseUsers = StreamSupport
                .stream(iterable.spliterator(), false)
                .map(RequestOrder::new)
                .toList();

        return ResponseEntity.ok(responseUsers);
    }

}
