package com.weedrop.api.controllers

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

@Api(tags = ["Account"])
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/products")
class ProductController {

    private val productService = ProductService()

    @Context
    private var servletRequest: HttpServletRequest? = null

    @POST
    @ApiOperation(value = "Create a category.", response = ResponseDTO::class)
    fun createProduct(productCreationDTO: ProductCreationDTO) : Response {
        val response = productService.create(productCreationDTO)
        return response.buildResponse()
    }

    fun updateProduct() : Response {
        val response = ResponseDTO()
        return response.buildResponse()
    }

    @GET
    @Path("/{id}")
    fun getProductById(@PathParam("id") id: String): Response {
        val response = productService.getById(id)
        return response.buildResponse()
    }
}
