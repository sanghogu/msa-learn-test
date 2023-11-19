package com.example.orderservice.service;

import com.example.orderservice.entity.Order;
import com.example.orderservice.entity.OrderItem;
import com.example.orderservice.pojo.RequestOrder;
import com.example.orderservice.repository.OrderItemRepository;
import com.example.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderService(OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }


    @Transactional
    public void createOrder(RequestOrder requestOrder){

        Order order = new Order(requestOrder.getIdentityName(), requestOrder.getBuyerName(), requestOrder.getUserId());

        List<OrderItem> orderItems = requestOrder
                .getOrderItems()
                .stream()
                .map(oi->new OrderItem(oi.getName(), oi.getUnitPrice(), oi.getQty(), oi.getProductId(), order))
                .toList();

        orderRepository.save(order);
        orderItemRepository.saveAll(orderItems);

    }

    public Order findByIdentityName(String identityName) {
        return orderRepository.findByIdentityName(identityName);
    }

    public Iterable<Order> findAll() {
        return orderRepository.findAll();
    }
}
