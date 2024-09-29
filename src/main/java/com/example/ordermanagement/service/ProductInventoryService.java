package com.example.ordermanagement.service;


import com.example.ordermanagement.model.dto.ItemDto;
import com.example.ordermanagement.model.dto.ProductDto;

import java.util.List;

public interface ProductInventoryService {
    ItemDto save(ProductDto productDto);

    List<ItemDto> findAll();

    ItemDto findById(Long id);

    void update(ProductDto ProductDto, Long id);

}
