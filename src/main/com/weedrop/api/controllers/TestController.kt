package com.weedrop.api.controllers

import com.weedrop.api.beans.dto.ResponseDTO
import com.weedrop.api.database.DAOManager
import com.weedrop.api.helpers.sendEmail
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response


@Path("/test")
@Consumes(MediaType.APPLICATION_JSON)
class TestController {


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun baseTest(): Response {
        return ResponseDTO(data = "API RUNS !!").buildResponse()
    }

    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    fun getTest(): Response {
        return ResponseDTO(data = DAOManager.factory.userDAO.first).buildResponse()
    }

    @GET
    @Path("/testemail")
    @Produces(MediaType.APPLICATION_JSON)
    fun getTestEmail(): Response {
        if (!sendEmail("hugo.walbecq@gmail.com", "test", "Email works !", null, this.javaClass))
            return ResponseDTO(error = "cannot send email").buildResponse()
        return ResponseDTO(data = null).buildResponse()
    }

}
