package com.weedrop.api.controllers

import com.weedrop.api.annotations.Authenticated
import com.weedrop.api.beans.User
import com.weedrop.api.beans.dto.ResponseDTO
import com.weedrop.api.beans.dto.ShopCreationDTO
import com.weedrop.api.services.ShopService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import javax.servlet.http.HttpServletRequest
import javax.ws.rs.*
import javax.ws.rs.core.Context
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Api(tags = ["Shop"])
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/shop")
@Authenticated
class ShopController {

    private val shopService = ShopService()

    @Context
    private var servletRequest: HttpServletRequest? = null

    @POST
    @ApiOperation(value = "Create a shop.", response = ResponseDTO::class)
    fun createProduct(shopCreationDTO: ShopCreationDTO) : Response {
        val sender = servletRequest!!.getAttribute("user") as User
        val response = shopService.create(shopCreationDTO, sender)
        return response.buildResponse()
    }

    @GET
    @ApiOperation(value = "Get all shops.", response = ResponseDTO::class)
    fun getShops() : Response {
        val response = shopService.getShops()
        return response.buildResponse()
    }

}
