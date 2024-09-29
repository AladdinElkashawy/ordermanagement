package com.example.ordermanagement.controller;


import com.example.ordermanagement.model.dto.CartDto;
import com.example.ordermanagement.model.dto.OrderDto;
import com.example.ordermanagement.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("order")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("save")
    public void saveApi(@RequestBody OrderDto orderDto) {
        orderService.save(orderDto);
    }

    @GetMapping("find/{id}")
    public CartDto findByIdApi(@PathVariable Long id) {
        return orderService.findById(id);
    }

    @GetMapping("findAll")
    public List<CartDto> findAllApi() {
        return orderService.findAll();
    }



}
