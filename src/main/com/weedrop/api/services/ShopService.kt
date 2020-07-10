package com.weedrop.api.services

import com.weedrop.api.beans.Shop
import com.weedrop.api.beans.User
import com.weedrop.api.beans.dto.ResponseDTO
import com.weedrop.api.beans.dto.ShopCreationDTO
import com.weedrop.api.database.DAOManager

class ShopService {
    fun getShops() : ResponseDTO {
        return ResponseDTO()
    }

    fun create(shopCreationDTO: ShopCreationDTO, sender: User): ResponseDTO {
        val shop = Shop(shopCreationDTO, sender)
        DAOManager.factory.shopDAO.push(shop)
        return ResponseDTO(data = shop)
    }
}
