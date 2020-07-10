package com.weedrop.api.controllers

import com.weedrop.api.beans.User
import com.weedrop.api.beans.dto.OrderCreationDTO
import com.weedrop.api.beans.dto.ResponseDTO
import com.weedrop.api.beans.dto.SignupDTO
import com.weedrop.api.services.AccountService
import com.weedrop.api.services.OrderService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import javax.servlet.http.HttpServletRequest
import javax.ws.rs.*
import javax.ws.rs.core.Context
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Api(tags = ["Order"])
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/orders")
class OrderController {

    private val orderService = OrderService()

    @Context
    private var servletRequest: HttpServletRequest? = null

    @POST
    @ApiOperation(value = "Create an order.", response = ResponseDTO::class)
    fun createOrder(orderCreationDTO: OrderCreationDTO) : Response {
        val response = orderService.createOrder(orderCreationDTO)
        return response.buildResponse()
    }

    @GET
    fun getOrders() : Response {
        val sender = servletRequest!!.getAttribute("user") as User
        val response = orderService.getOrders(sender)
        return response.buildResponse()
    }

    @GET
    @Path("/{id}")
    fun getOrderById(@PathParam("id") id: String): Response {
        val response = orderService.getOrderById(id)
        return response.buildResponse()
    }

    @POST
    @Path("/{id}/validate")
    fun validateOrder(@PathParam("id") id: String) : Response {
        val response = orderService.validateOrder(id)
        return response.buildResponse()
    }

    @POST
    @Path("/{id}/decline")
    fun declineOrder(@PathParam("id") id: String) : Response {
        val response = orderService.declineOrder(id)
        return response.buildResponse()
    }
}
