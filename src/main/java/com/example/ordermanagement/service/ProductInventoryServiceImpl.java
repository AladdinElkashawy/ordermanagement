package com.example.ordermanagement.service;



import com.example.ordermanagement.error.exeption.NotValidProductException;
import com.example.ordermanagement.error.exeption.ProductNotFoundException;
import com.example.ordermanagement.model.dto.ItemDto;
import com.example.ordermanagement.model.dto.ProductDto;
import com.example.ordermanagement.model.entity.Inventory;
import com.example.ordermanagement.model.entity.Product;
import com.example.ordermanagement.repository.InventoryRepo;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ProductInventoryServiceImpl implements ProductInventoryService {

    private final InventoryRepo inventoryRepo;

    @Autowired
    public ProductInventoryServiceImpl(InventoryRepo inventoryRepo) {
        this.inventoryRepo = inventoryRepo;

    }

    @Transactional
    public ItemDto save(ProductDto productDto) {
        if (!productDto.Valid()) {
            log.error("product price and quantity must be greater than 0");
            throw new NotValidProductException("product price and quantity must be greater than 0");
        }
        return inventoryRepo.save(productDto.toInventory()).toItemDto();
    }

    public List<ItemDto> findAll() {
        return inventoryRepo.findAll().stream().map(Inventory::toItemDto).toList();
    }

    @Override
    public ItemDto findById(Long id) {
        Inventory inventory = inventoryRepo.findById(id).orElseThrow(() -> {
            log.error("product with this id not exist");
            throw new ProductNotFoundException("product with this id not exist");
        });
        return inventory.toItemDto();
    }

    @Transactional
    public void update(ProductDto productDto, Long id) {

        if (!productDto.Valid()) {
            log.error("product price and quantity must be greater than 0");
            throw new NotValidProductException("product price and quantity must be greater than 0");
        }
        Inventory inventory = inventoryRepo.findById(id).orElseThrow(() -> {
            log.error("inventory Not Found");
            throw new ProductNotFoundException("product Not Found ");
        });

        Product product = inventory.getProduct();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        inventory.setQuantity(productDto.getQuantity());
        inventory.setProduct(product);
        inventoryRepo.save(inventory);
    }


}