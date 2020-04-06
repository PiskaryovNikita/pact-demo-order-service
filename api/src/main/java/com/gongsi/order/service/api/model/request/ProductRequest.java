package com.gongsi.order.service.api.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {
    private long id;
    private String name;
    private double price;

    public ProductRequest(String name, double price) {
        this.name = name;
        this.price = price;
    }
}
