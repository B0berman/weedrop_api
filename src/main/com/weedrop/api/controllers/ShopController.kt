package com.weedrop.api.controllers

import com.weedrop.api.services.ShopService
import io.swagger.annotations.Api
import javax.servlet.http.HttpServletRequest
import javax.ws.rs.*
import javax.ws.rs.core.Context
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Api(tags = ["Shop"])
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/shop")
//@Authenticated
class ShopController {

    private val shopService = ShopService()

    @Context
    private var servletRequest: HttpServletRequest? = null

    @GET
    fun getShops() : Response {
        val response = shopService.getShops()
        return response.buildResponse()
    }

}
