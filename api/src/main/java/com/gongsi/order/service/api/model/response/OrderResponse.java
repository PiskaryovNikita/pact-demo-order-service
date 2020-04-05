package com.gongsi.order.service.api.model.response;

import com.gongsi.product.management.api.model.response.ProductResponse;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    private User user;
    private OffsetDateTime orderDate;
    private ProductResponse product;
}
