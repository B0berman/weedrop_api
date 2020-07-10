package com.weedrop.api.controllers

import com.weedrop.api.beans.dto.ResponseDTO
import com.weedrop.api.database.DAOManager
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

}
