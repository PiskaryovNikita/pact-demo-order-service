package com.gongsi.order.service.impl.pact.consumer;

import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.core.model.annotations.Pact;
import au.com.dius.pact.consumer.junit.PactProviderRule;
import au.com.dius.pact.consumer.junit.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.core.model.RequestResponsePact;
import com.gongsi.order.service.impl.OrderServiceConfig;
import com.gongsi.order.service.impl.ProductService;
import com.gongsi.product.management.api.model.request.ProductRequest;
import com.gongsi.product.management.api.model.response.ProductResponse;
import javax.ws.rs.core.MediaType;
import org.apache.http.HttpHeaders;
import org.junit.Rule;
import org.junit.Test;

import java.util.HashMap;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {OrderServiceConfig.class})
@TestPropertySource(locations = "/application-test.properties")
public class OrderServiceConsumerTest {
    private static final String CONSUMER = "order-service";
    private static final String PROVIDER = "product-management";

    @Autowired
    private ProductService productService;

    @Rule
    public PactProviderRule mockTestProvider = new PactProviderRule("product-management", "localhost", 8112,
            this);

    @Pact(consumer= CONSUMER, provider = PROVIDER)
    public RequestResponsePact getProductFragment(PactDslWithProvider builder) {
        PactDslJsonBody getProductResponse = new PactDslJsonBody()
                .numberType("id")
                .stringType("name")
                .numberType("price");

        return builder
                .given("get order state")
                .uponReceiving("get order request")
                .path("/api/products/1")
                .method("GET")
                .willRespondWith()
                .status(200)
                .body(getProductResponse)
                .toPact();
    }

    //when using fragments then provider value must be specified
    @Test
    @PactVerification(value = PROVIDER, fragment = "getProductFragment")
    public void verifyGetProductRequest() {
        final ProductResponse product = productService.getProduct(1L);

        //nonsense assertion, since this was specified when creating pact fragment
        assertNotNull(product);
    }
}

