package com.example.ordermanagement.controller;


import com.example.ordermanagement.model.dto.ItemDto;
import com.example.ordermanagement.model.dto.ProductDto;
import com.example.ordermanagement.service.ProductInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("inventory")
public class InventoryController {
    private final ProductInventoryService productInventoryService;

    @Autowired
    public InventoryController(ProductInventoryService productInventoryService) {
        this.productInventoryService = productInventoryService;
    }


    @PostMapping("save")
    public ItemDto saveApi(@RequestBody ProductDto productDto) {
        return productInventoryService.save(productDto);
    }


    @GetMapping("find/{id}")
    public ItemDto findByIdApi(@PathVariable Long id) {
        return productInventoryService.findById(id);
    }

    @GetMapping("findAll")
    public List<ItemDto> findAllApi() {
        return productInventoryService.findAll();
    }


    @PutMapping("update/{id}")
    public void updateApi(@RequestBody ProductDto productDto, @PathVariable Long id) {
        productInventoryService.update(productDto, id);
    }
}
