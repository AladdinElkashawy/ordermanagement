package com.example.ordermanagement.service;


import com.example.ordermanagement.error.exeption.*;
import com.example.ordermanagement.model.dto.CartDto;
import com.example.ordermanagement.model.dto.ItemDto;
import com.example.ordermanagement.model.dto.OrderDto;
import com.example.ordermanagement.model.entity.Inventory;
import com.example.ordermanagement.model.entity.Order;
import com.example.ordermanagement.model.entity.OrderItem;
import com.example.ordermanagement.model.entity.Product;
import com.example.ordermanagement.repository.InventoryRepo;
import com.example.ordermanagement.repository.OrderRepo;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepo orderRepo;
    private final InventoryRepo inventoryRepo;

    @Autowired
    public OrderServiceImpl(OrderRepo orderRepo, InventoryRepo inventoryRepo) {
        this.orderRepo = orderRepo;
        this.inventoryRepo = inventoryRepo;
    }

    @Transactional
    public void save(OrderDto orderDto) {

        if (orderDto.getProducts().isEmpty()) {
            log.error("Order not have any products.");
            throw new EmptyProductsException("order not have any produts");
        }
        Set<Product> productSet = new HashSet<>();
        //validate order
        for (ItemDto itemDto : orderDto.getProducts()) {
            if (!itemDto.Valid()) {
                log.error("product price and quantity must be greater than 0");
                throw new NotValidProductException("product price and quantity must be greater than 0");
            }
            Inventory inventory = inventoryRepo.findById(itemDto.getId()).orElseThrow(() -> {
                log.error("Product not found with id: " + itemDto.getId());
                throw new ProductNotFoundException("Product not found with id: " + itemDto.getId());
            });
            if (inventory.getQuantity() < itemDto.getQuantity()) {
                log.error("Product  with id: " + itemDto.getId() + " stock is not enough");
                throw new InsufficientStockException("Product  with id: " + itemDto.getId() + " stock is not enough");
            }
            if (productSet.contains(inventory.getProduct())) {
                log.error("Each product should exist one time in the order.");
                throw new ProductDuplicateException("Each product should exist one time in the order.");
            }
            productSet.add(inventory.getProduct());
        }
        ///make order
        double totalPrice = 0;
        Order order = new Order();
        List<OrderItem> orderItems = new ArrayList<>();
        for (ItemDto itemDto : orderDto.getProducts()) {
            Inventory inventory = inventoryRepo.findById(itemDto.getId()).get();
            inventory.setQuantity(inventory.getQuantity() - itemDto.getQuantity());
            inventoryRepo.save(inventory);
            OrderItem orderItem = OrderItem.builder().product(inventory.getProduct()).orders(order).quantity(itemDto.getQuantity()).build();
            totalPrice += (itemDto.getQuantity() * inventory.getProduct().getPrice());
            orderItems.add(orderItem);
        }
        order.setOrderItems(orderItems);
        order.setTotalPrice(totalPrice);
        orderRepo.save(order);

    }

    public CartDto findById(Long id) {
        Order order = orderRepo.findById(id).orElseThrow(() -> {
            log.error("order Not Found");
            throw new OrderNotFoundException("order Not Found");
        });
        CartDto cartDto = new CartDto();
        cartDto.setId(order.getId());
        cartDto.setTotalPrice(order.getTotalPrice());
        cartDto.setProducts(order.getOrderItems().stream().map(OrderItem::toProductDto).toList());
        return cartDto;
    }

    public List<CartDto> findAll() {
        return orderRepo.findAll().stream().map(Order::toOrderDto).toList();
    }
}