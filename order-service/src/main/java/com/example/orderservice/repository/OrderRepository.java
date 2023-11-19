package com.example.orderservice.repository;

import com.example.orderservice.entity.Order;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface OrderRepository extends JpaRepository<Order, Long> {

    @EntityGraph(attributePaths = {"orderItems"}, type = EntityGraph.EntityGraphType.LOAD)
    Order findByIdentityName(@NonNull String identityName);

    @EntityGraph(attributePaths = {"orderItems"}, type = EntityGraph.EntityGraphType.LOAD)
    List<Order> findAll();

}
