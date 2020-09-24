package com.weedrop.api.services

import com.weedrop.api.beans.Shop
import com.weedrop.api.beans.User
import com.weedrop.api.beans.dto.ResponseDTO
import com.weedrop.api.beans.dto.ShopCreationDTO
import com.weedrop.api.beans.dto.ShopDTO
import com.weedrop.api.database.DAOManager
import com.weedrop.api.helpers.sendEmail

class ShopService {
    fun getShops() : ResponseDTO {
        val shops = DAOManager.factory.shopDAO.all
        return ResponseDTO(data = shops)
    }

    fun create(shopCreationDTO: ShopCreationDTO, sender: User): ResponseDTO {
        val shop = Shop(shopCreationDTO, sender)
        sender.hasShop = true
        DAOManager.factory.userDAO.push(sender)
        DAOManager.factory.shopDAO.push(shop)
        return ResponseDTO(data = shop)
    }

    fun getUserShop(sender: User) : ResponseDTO {
        val shop = DAOManager.factory.shopDAO.filter("admin", sender).first
                ?: return ResponseDTO(error = "User isn't admin of any shop")
        val products = DAOManager.factory.productDAO.filter("shop", shop).all
        for (product in products)
            product.shop = null
        val result = ShopDTO(shop, products)
        return ResponseDTO(data = result)
    }

    fun activateShop(id: String) : ResponseDTO {
        val shop = DAOManager.factory.shopDAO.filter("id", id).first
                ?: return ResponseDTO(error = "Invalid shop id")
        shop.isActive = true
        if (!sendEmail(shop.admin.email, "SHOP ACTIVATION", "Your shop has been activated by the Weedrop services.\n\nThank you !", null, this.javaClass))
        DAOManager.factory.shopDAO.push(shop)
        return ResponseDTO(data = shop)
    }
}
