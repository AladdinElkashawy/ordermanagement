package com.example.ordermanagement.model.entity;

import com.example.ordermanagement.model.dto.CartDto;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "Web_order")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;
    @Column(name = "total_price")
    private double totalPrice;

    @JsonManagedReference
    @OneToMany(mappedBy = "orders",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
    private List<OrderItem> orderItems;

    public CartDto toOrderDto(){
        return CartDto.builder().id(id).products(orderItems.stream().map(OrderItem::toProductDto).toList()).build();
    }
}


