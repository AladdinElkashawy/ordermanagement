package com.example.ordermanagement.model.entity;


import com.example.ordermanagement.model.dto.ItemDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Inventory")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Inventory {
    @Id
    @Column(name = "Inventory_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    private Product product;
    @Column(name = "quantity")
    private int quantity;
    public ItemDto toItemDto(){
        return ItemDto.builder().id(id).name(product.getName()).price(product.getPrice()).quantity(quantity).build();
    }
}

