package com.gongsi.order.service.impl;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.json.JsonWriteFeature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gongsi.product.management.api.model.request.ProductRequest;
import com.gongsi.product.management.api.model.response.ProductResponse;
import java.io.IOException;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.core.MediaType;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;

public class ProductService {
    @Value("${service.path.product.management}")
    private String productManagementPath;

    public ProductResponse getProduct(long id) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpGet request = new HttpGet(String.format("%s/api/products/1", productManagementPath));
            request.addHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
            String entity;
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                entity = EntityUtils.toString(response.getEntity());
            }
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(entity, ProductResponse.class);
        } catch (IOException e) {
            throw new InternalServerErrorException(e.getMessage(), e);
        }
    }
}
