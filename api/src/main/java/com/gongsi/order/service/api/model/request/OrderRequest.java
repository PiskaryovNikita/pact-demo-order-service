package com.gongsi.order.service.api.model.request;

import com.gongsi.order.service.api.model.response.User;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    private User user;
    private OffsetDateTime orderDate;
    private ProductRequest product;
}
