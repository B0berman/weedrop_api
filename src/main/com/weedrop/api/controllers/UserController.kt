package com.weedrop.api.controllers

import com.weedrop.api.annotations.Authenticated
import com.weedrop.api.beans.dto.ResponseDTO
import com.weedrop.api.services.UserService
import io.swagger.annotations.Api
import javax.servlet.http.HttpServletRequest
import javax.ws.rs.*
import javax.ws.rs.core.Context
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Api(tags = ["Users"])
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/users")
//@Authenticated
class UserController {

    private val userService = UserService()

    @Context
    private var servletRequest: HttpServletRequest? = null

    @GET
    @Path("/current")
    @Authenticated
    fun getCurrentUser() : Response {
        val user = servletRequest!!.getAttribute("user")
        return ResponseDTO(data = user).buildResponse()
    }

    @GET
    @Path("/{id}")
    @Authenticated(["ADMIN"])
    fun getUserById(@PathParam("id") id: String) : Response {
        val response = userService.getUserById(id)
        return response.buildResponse()
    }
}
