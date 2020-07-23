package com.weedrop.api.filters

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.weedrop.api.annotations.Authenticated
import com.weedrop.api.beans.User
import com.weedrop.api.beans.dto.ResponseDTO
import com.weedrop.api.database.DAOManager
import javax.ws.rs.container.ContainerRequestContext
import javax.ws.rs.container.ContainerRequestFilter
import javax.ws.rs.container.ResourceInfo
import javax.ws.rs.core.Context
import javax.ws.rs.core.Response
import javax.ws.rs.ext.Provider


@Provider
class AuthenticationFilter : ContainerRequestFilter {

    @Context
    private lateinit var resourceInfo: ResourceInfo

    override fun filter(requestContext: ContainerRequestContext) {
        val method = resourceInfo.resourceMethod
        val rClass = resourceInfo.resourceClass
        var annotation: Authenticated? = null

        if (rClass.isAnnotationPresent(Authenticated::class.java))
            annotation = rClass.getAnnotation(Authenticated::class.java) as Authenticated
        else if (method.isAnnotationPresent(Authenticated::class.java))
            annotation = method.getAnnotation(Authenticated::class.java) as Authenticated

        if (rClass != null && annotation != null) {
            val rolesAllowed = annotation.level
            if (!requestContext.headers.containsKey("Authorization"))
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity(ResponseDTO(error = "Missing token")).build())
            var token: String? = requestContext.headers.getFirst("Authorization")
            requestContext.setProperty("token", token)
            if (token == null || token.isEmpty())
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity(ResponseDTO(error = "Missing or empty token")).build())
            else {
                if (!token.contains("Bearer "))
                    requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity(ResponseDTO(error = "Bad formatted token")).build())
                token = token.replaceFirst(("Bearer" + " ").toRegex(), "")
                try {
                    val firebaseToken = FirebaseAuth.getInstance().verifyIdToken(token, true)
                    val user: User? = DAOManager.factory.userDAO.filter("email", firebaseToken.email).first
                    if (user == null)
                        requestContext.abortWith(ResponseDTO(error = "Invalid token", status = 401).buildResponse())
                    println(user)
                    requestContext.setProperty("user", user!!)
                } catch (e: FirebaseAuthException) {
                    e.printStackTrace()
                    requestContext.abortWith(ResponseDTO(error = "Invalid token", status = 401).buildResponse())
                }
            }
        }
    }
}
