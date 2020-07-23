package com.weedrop.api.services

import com.weedrop.api.beans.Product
import com.weedrop.api.beans.User
import com.weedrop.api.beans.dto.ProductCreationDTO
import com.weedrop.api.beans.dto.ResponseDTO
import com.weedrop.api.database.DAOManager

class ProductService {

    fun getById(id: String) : ResponseDTO {
        val product = DAOManager.factory.productDAO.filter("id", id).first
                ?: return ResponseDTO(error = "Invalid id")
        return ResponseDTO(data = product)
    }

    fun create(productCreationDTO: ProductCreationDTO, sender: User): ResponseDTO {
        val shop = DAOManager.factory.shopDAO.filter("admin", sender).first
                ?: return ResponseDTO(error = "User isn't admin of any shop")
        val product = Product(productCreationDTO, shop)
        DAOManager.factory.productDAO.push(product)
        return ResponseDTO(data = product)
    }

    fun getAll(): ResponseDTO {
        val products = DAOManager.factory.productDAO.all
        return ResponseDTO(data = products)
    }
}
