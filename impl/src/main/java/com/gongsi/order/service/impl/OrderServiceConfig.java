package com.gongsi.order.service.impl;

import com.gongsi.order.service.api.OrderController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderServiceConfig {

    @Bean
    public ProductService productService() {
        return new ProductService();
    }

    @Bean
    public OrderController orderController(ProductService productService) {
        return new OrderControllerImpl(productService);
    }

}
