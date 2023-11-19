package com.example.orderservice.controller;

import com.example.orderservice.entity.Order;
import com.example.orderservice.pojo.RequestOrder;
import com.example.orderservice.pojo.ResponseOrder;
import com.example.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @GetMapping("/health")
    @ResponseBody
    public String index(){

        return "OK";
    }

    @PostMapping("/orders")
    @ResponseBody
    public ResponseEntity<ResponseOrder> createUser(@RequestBody RequestOrder requestOrder) {

        Order order = orderService.createOrder(requestOrder);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseOrder(order, order.getOrderItems()));
    }

    @GetMapping("/welcome")
    @ResponseBody
    public String welcome(){
        return greetingMessage;
    }

    @GetMapping("/orders/{identityName}")
    @ResponseBody
    public ResponseEntity<ResponseOrder> getUsers(@PathVariable String identityName) {

        Order order = orderService.findByIdentityName(identityName);

        ResponseOrder responseOrder = order == null ? null : new ResponseOrder(order, order.getOrderItems());

        return ResponseEntity.ok(responseOrder);
    }

    @GetMapping("/orders")
    @ResponseBody
    public ResponseEntity<List<ResponseOrder>> getUsers(){

        Iterable<Order> iterable = orderService.findAll();

        List<ResponseOrder> responseUsers = StreamSupport
                .stream(iterable.spliterator(), false)
                .map(o->new ResponseOrder(o, o.getOrderItems()))
                .toList();

        return ResponseEntity.ok(responseUsers);
    }

}
