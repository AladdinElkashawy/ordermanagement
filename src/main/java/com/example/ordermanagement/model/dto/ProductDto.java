package com.example.ordermanagement.model.dto;

import com.example.ordermanagement.model.entity.Inventory;
import com.example.ordermanagement.model.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class ProductDto {


    private String name;
    private double price;
    private int quantity;


    public Boolean Valid(){
        if(price>0&&quantity>0){
            return true;
        }
        else {
            return false;
        }
    }
    public Product toProduct(){
        return Product.builder().name(name).price(price).build();
    }
    public Inventory toInventory(){
        return Inventory.builder().product(toProduct()).quantity(quantity).build();
    }

}
