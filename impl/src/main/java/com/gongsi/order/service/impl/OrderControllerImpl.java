package com.gongsi.order.service.impl;

import com.gongsi.order.service.api.OrderController;
import com.gongsi.order.service.api.model.request.OrderRequest;
import com.gongsi.order.service.api.model.response.OrderResponse;
import com.gongsi.order.service.api.model.response.ProductResponse;
import com.gongsi.order.service.api.model.response.User;
import java.time.OffsetDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderControllerImpl implements OrderController {
    private final ProductService productService;

    @Override
    public String getVersion() {
        return Optional.ofNullable(getClass().getPackage())
                .map(Package::getImplementationVersion)
                .orElse("UNKNOWN");
    }

    @Override
    public OrderResponse createOrder(long id) {
        User user = new User(1L, "Name", "e@e.com");
        final ProductResponse productResponse = productService.getProduct(1L);

        return new OrderResponse(user, OffsetDateTime.now(), productResponse);
    }

    @Override
    public OrderResponse createOrder(OrderRequest request) {
        User user = new User(1L, "Name", "e@e.com");
        final ProductResponse productResponse = productService.createProduct(request.getProduct());

        return new OrderResponse(user, OffsetDateTime.now(), productResponse);
    }
}
