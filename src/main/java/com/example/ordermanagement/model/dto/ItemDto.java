package com.example.ordermanagement.model.dto;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class ItemDto {

    private Long id;
    private String name;
    private double price;
    private int quantity;


    public Boolean Valid() {
        if (price > 0 && quantity > 0) {
            return true;
        } else {
            return false;
        }
    }


}
