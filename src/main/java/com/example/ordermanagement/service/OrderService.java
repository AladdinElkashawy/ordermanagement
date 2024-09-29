package com.example.ordermanagement.service;


import com.example.ordermanagement.model.dto.CartDto;
import com.example.ordermanagement.model.dto.OrderDto;

import java.util.List;

public interface OrderService {
    public void save(OrderDto orderDto);
    public CartDto findById(Long id);
    public List<CartDto> findAll();

}
