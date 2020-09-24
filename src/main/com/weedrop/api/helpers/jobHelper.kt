package com.weedrop.api.helpers

import com.weedrop.api.beans.User
import com.weedrop.api.database.DAOManager
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import java.io.IOException
import java.lang.Long
import java.sql.Timestamp
import java.util.*

fun getProperty(key: String, aClass: Class<*>, file: String): String {
    val props = Properties()
    return try {
        val inputStream = aClass.classLoader.getResourceAsStream("$file.properties")
        props.load(inputStream!!)
        props.getProperty(key)
    } catch (e: IOException) {
        e.printStackTrace()
        ""
    }
}

fun encryptCode(aClass: Class<*>, code: String, email: String): String = Jwts.builder() // VALIDITY 1 DAY
        .setExpiration(Timestamp(System.currentTimeMillis() + 1 * 60 * 60 * 24 * 1000))
        .claim("code", code)
        .claim("email", email)
        .signWith(SignatureAlgorithm.HS256, getProperty("signing_key", aClass, "keys"))
        .compact()

fun isValidCode(code: String, aClass: Class<*>): User? {
    return try {
        val key: String = getProperty("signing_key", aClass, "keys")
        val claims = Jwts.parser().setSigningKey(key).parseClaimsJws(code).body
        Date(Long.valueOf(claims["exp"].toString()) * 1000)
        val email = claims["email"];
        val user = DAOManager.factory.userDAO.filter("email", email).first
                ?: return null
        user
    } catch (e: Exception) {
        null
    }
}
