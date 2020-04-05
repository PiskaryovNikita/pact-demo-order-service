package com.gongsi.order.service.api;

import com.gongsi.order.service.api.model.response.OrderResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
    OrderResponse getOrder(@PathParam("id") long id);
}
