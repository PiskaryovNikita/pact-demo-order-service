package com.gongsi.order.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gongsi.order.service.api.OrderController;
import com.gongsi.order.service.api.model.request.OrderRequest;
import com.gongsi.order.service.api.model.response.OrderResponse;
import com.gongsi.order.service.api.model.response.User;
import com.gongsi.product.management.api.ProductController;
import com.gongsi.product.management.api.model.response.ProductResponse;
import java.io.IOException;
import java.io.InputStream;
import java.time.OffsetDateTime;
import java.util.Optional;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpHeaders;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;

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
