package com.weedrop.api.services

import com.weedrop.api.beans.Product
import com.weedrop.api.beans.dto.ProductCreationDTO
import com.weedrop.api.beans.dto.ResponseDTO
import com.weedrop.api.database.DAOManager

class ProductService {

    fun getById(id: String) : ResponseDTO {
        val product = DAOManager.factory.productDAO.filter("id", id).first
                ?: return ResponseDTO(error = "Invalid id")
        return ResponseDTO(data = product)
    }

    fun create(productCreationDTO: ProductCreationDTO): ResponseDTO {
        val product = Product(productCreationDTO)
        DAOManager.factory.productDAO.push(product)
        return ResponseDTO(data = product)
    }
}
