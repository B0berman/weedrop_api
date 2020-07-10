package com.weedrop.api.helpers

import java.io.IOException
import java.util.*

fun getProperty(key: String, aClass: Class<*>, file: String): String {
    val props = Properties()
    try {
        val inputStream = aClass.classLoader.getResourceAsStream("$file.properties")
        props.load(inputStream!!)
        return props.getProperty(key)
    } catch (e: IOException) {
        e.printStackTrace()
    }
    return ""
}
