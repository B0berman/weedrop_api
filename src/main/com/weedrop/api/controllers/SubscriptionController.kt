package com.weedrop.api.controllers

import com.weedrop.api.annotations.Authenticated
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

@Api(tags = ["Subscription"])
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/subscriptions")
@Authenticated
class SubscriptionController {

    private val accountService = AccountService()

    @Context
    private var servletRequest: HttpServletRequest? = null

    @POST
    @Path("/subscribe")
    @ApiOperation(value = "Subscribe as seller.", response = ResponseDTO::class)
    fun subscribe() : Response {
        val response = ResponseDTO()
        return response.buildResponse()
    }

    fun validateSubscription(): ResponseDTO {
        return ResponseDTO()
    }

    fun declineSubscription(): ResponseDTO {
        return ResponseDTO()
    }
}
