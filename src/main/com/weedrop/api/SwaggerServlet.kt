package com.weedrop.api

import javax.servlet.ServletConfig
import javax.servlet.ServletException
import javax.servlet.http.HttpServlet
import io.swagger.jaxrs.config.BeanConfig

class SwaggerServlet : HttpServlet() {

    @Throws(ServletException::class)
    override fun init(config: ServletConfig) {
        super.init(config)

        val beanConfig = BeanConfig()
        beanConfig.version = "1.0.1"
        beanConfig.filterClass = "com.weedrop.api.filters.AuthenticationFilter"
        beanConfig.title = "MidEvalApi"
        beanConfig.schemes = arrayOf("http")
        beanConfig.host = "163.172.186.194"
        beanConfig.basePath = "/weedrop-api"
        beanConfig.resourcePackage = "com.weedrop.api.controllers"
        beanConfig.scan = true
    }

}
