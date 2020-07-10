package com.weedrop.api.helpers

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts

fun getTokenClaims(token: String, aClass: Class<*>): Claims {
    return Jwts.parser()
            .setSigningKey("!SeC3tKyeIZeBoMyEss")
            .parseClaimsJws(token)
            .body

    //getProperty("signing_key", aClass, "keys"))
}
