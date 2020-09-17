package com.weedrop.api.controllers

import com.weedrop.api.annotations.Authenticated
import com.weedrop.api.beans.User
import com.weedrop.api.beans.dto.ResponseDTO
import com.weedrop.api.beans.dto.SignupDTO
import com.weedrop.api.beans.dto.SubscriptionFormDTO
import com.weedrop.api.services.AccountService
import com.weedrop.api.services.SubscriptionService
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

    private val subscriptionService = SubscriptionService()

    @Context
    private var servletRequest: HttpServletRequest? = null

    @POST
    @Path("/subscribe")
    @ApiOperation(value = "Subscribe as seller.", response = ResponseDTO::class)
    fun subscribe(form: SubscriptionFormDTO) : Response {
        val sender = servletRequest!!.getAttribute("user") as User
        val response = subscriptionService.subscribe(form, sender)
        return response.buildResponse()
    }

    @POST
    @Path("/validate/{id}")
    @Authenticated(["ADMIN"])
    fun validateSubscription(@PathParam("id") id: String): Response {
        val response = subscriptionService.validateSubscription(id)
        return response.buildResponse()
    }

    @POST
    @Path("/decline/{id}")
    @Authenticated(["ADMIN"])
    fun declineSubscription(@PathParam("id") id: String): Response {
        val response = subscriptionService.declineSubscription(id)
        return response.buildResponse()
    }
}
