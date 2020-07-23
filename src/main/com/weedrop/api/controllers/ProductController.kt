package com.weedrop.api.controllers

import com.weedrop.api.annotations.Authenticated
import com.weedrop.api.beans.User
import com.weedrop.api.beans.dto.ProductCreationDTO
import com.weedrop.api.beans.dto.ResponseDTO
import com.weedrop.api.beans.dto.SignupDTO
import com.weedrop.api.services.AccountService
import com.weedrop.api.services.ProductService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import javax.servlet.http.HttpServletRequest
import javax.ws.rs.*
import javax.ws.rs.core.Context
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Api(tags = ["Product"])
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/products")
class ProductController {

    private val productService = ProductService()

    @Context
    private var servletRequest: HttpServletRequest? = null

    @POST
    @ApiOperation(value = "Create a product.", response = ResponseDTO::class)
    @Authenticated
    fun createProduct(productCreationDTO: ProductCreationDTO) : Response {
        val sender = servletRequest!!.getAttribute("user") as User
        val response = productService.create(productCreationDTO, sender)
        return response.buildResponse()
    }

    @GET
    @ApiOperation(value = "Get a product by id.", response = ResponseDTO::class)
    fun getAllProduct(): Response {
        val response = productService.getAll()
        return response.buildResponse()
    }

    @PUT
    @Path("/{id}")
    @ApiOperation(value = "Update a product.", response = ResponseDTO::class)
    fun updateProduct(@PathParam("id") id: String) : Response {
        val response = ResponseDTO()
        return response.buildResponse()
    }

    @GET
    @Path("/{id}")
    @ApiOperation(value = "Get a product by id.", response = ResponseDTO::class)
    fun getProductById(@PathParam("id") id: String): Response {
        val response = productService.getById(id)
        return response.buildResponse()
    }
}
