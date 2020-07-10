package com.weedrop.api.controllers

import com.weedrop.api.beans.dto.ResponseDTO
import com.weedrop.api.beans.dto.SignupDTO
import com.weedrop.api.services.AccountService
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
@Path("/account")
class AccountController {

    private val accountService = AccountService()

    @Context
    private var servletRequest: HttpServletRequest? = null

    @POST
    @Path("/signup")
    @ApiOperation(value = "Create a category.", response = ResponseDTO::class)
    fun signup(signupDTO: SignupDTO) : Response {
        val response = accountService.signup(signupDTO)
        return response.buildResponse()
    }

    fun recoverPassword() : Response {
        return ResponseDTO().buildResponse()
    }

}
