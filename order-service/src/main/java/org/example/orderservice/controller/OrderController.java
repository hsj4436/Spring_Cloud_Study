package org.example.orderservice.controller;

import org.example.orderservice.dto.OrderDto;
import org.example.orderservice.jpa.OrderEntity;
import org.example.orderservice.service.OrderService;
import org.example.orderservice.vo.RequestOrder;
import org.example.orderservice.vo.ResponseOrder;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/order-service")
public class OrderController {

    private Environment env;
    private OrderService orderService;

    @Autowired
    public OrderController(Environment env, OrderService orderService) {
        this.env = env;
        this.orderService = orderService;
    }

    @GetMapping("/health_check")
    public String status() {
        return String.format("It's Working in Order Service on PORT %s", env.getProperty("local.server.port"));
    }

    @GetMapping("/{userId}/orders")
    public ResponseEntity<List<ResponseOrder>> getOrders(@PathVariable String userId) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        Iterable<OrderEntity> orderEntities = orderService.getOrdersByUserId(userId);

        List<ResponseOrder> orderList = new ArrayList<>();
        orderEntities.forEach(orderEntity -> {
            orderList.add(mapper.map(orderEntity, ResponseOrder.class));
        });

        return ResponseEntity.status(HttpStatus.OK).body(orderList);
    }

    @PostMapping("/{userId}/orders")
    public ResponseEntity<ResponseOrder> createOrder(@PathVariable String userId, @RequestBody RequestOrder orderDetails) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        OrderDto orderDto = mapper.map(orderDetails, OrderDto.class);
        orderDto.setUserId(userId);

        OrderDto createdOrderDto = orderService.createOrder(orderDto);

        ResponseOrder responseOrder = mapper.map(createdOrderDto, ResponseOrder.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseOrder);
    }
}
