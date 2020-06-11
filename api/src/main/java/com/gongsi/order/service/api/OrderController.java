package com.gongsi.order.service.api;

import com.gongsi.order.service.api.model.request.OrderRequest;
import com.gongsi.order.service.api.model.response.OrderResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/")
@Api
public interface OrderController {

    @GET
    @Path("version")
    @ApiOperation("get project version")
    String getVersion();

    @GET
    @Path("orders/{id}")
    @ApiOperation("get order")
    OrderResponse createOrder(@PathParam("id") long id);

    @POST
    @Path("orders")
    @ApiOperation("create order")
    OrderResponse createOrder(OrderRequest request);

    @PUT
    @Path("orders")
    @ApiOperation("create order")
    OrderResponse updateOrder(OrderRequest request);
}
