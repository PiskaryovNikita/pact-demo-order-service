package com.gongsi.order.service.app;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.apache.cxf.jaxrs.validation.JAXRSBeanValidationInInterceptor;
import org.apache.cxf.jaxrs.validation.JAXRSBeanValidationOutInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/** Example creating proxy-client based on interface
 BookStore store = JAXRSClientFactory.create("http://bookstore.com", BookStore.class);
 // (1) remote GET call to http://bookstore.com/bookstore
 Books books = store.getAllBooks();
 */
@Configuration
public class OrderApplicationConfig {

    // enables validation, extends cxf server, does not overwrite it
    @Bean
    public JAXRSBeanValidationInInterceptor jaxrsBeanValidationInInterceptor() {
        return new JAXRSBeanValidationInInterceptor();
    }

    @Bean
    public JAXRSBeanValidationOutInterceptor jaxrsBeanValidationOutInterceptor() {
        return new JAXRSBeanValidationOutInterceptor();
    }

    @Primary
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        SimpleModule simpleModule = new SimpleModule();
        objectMapper.registerModule(simpleModule);

        return objectMapper;
    }

    // Jackson provides java to json conversion
    @Bean
    public JacksonJsonProvider jacksonJsonProvider(ObjectMapper objectMapper) {
        JacksonJsonProvider jsonProvider = new JacksonJsonProvider();
        jsonProvider.setMapper(objectMapper);
        return jsonProvider;
    }
}
