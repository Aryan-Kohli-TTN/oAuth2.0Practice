package com.example.orderresourceserver.controller;

import com.example.orderresourceserver.OrderStatus;
import com.example.orderresourceserver.OrdersRest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class OrdersController {

    @GetMapping("/orders")
    public List<OrdersRest> getOrders() {
        List<OrdersRest> orders = new ArrayList<>();

        orders.add(new OrdersRest("O1001", "P101", "U001", 250.0, 2, OrderStatus.NEW));
        orders.add(new OrdersRest("O1002", "P102", "U002", 120.0, 1, OrderStatus.APPROVED));
        orders.add(new OrdersRest("O1003", "P103", "U003", 500.0, 5, OrderStatus.REJECTED));
        orders.add(new OrdersRest("O1004", "P101", "U004", 300.0, 3, OrderStatus.NEW));
        orders.add(new OrdersRest("O1005", "P104", "U001", 150.0, 1, OrderStatus.APPROVED));

        return orders;
    }
}
