package com.weedrop.api.services

import com.weedrop.api.beans.Order
import com.weedrop.api.beans.User
import com.weedrop.api.beans.dto.OrderCreationDTO
import com.weedrop.api.beans.dto.ResponseDTO
import com.weedrop.api.database.DAOManager

class OrderService {
    fun createOrder(orderCreationDTO: OrderCreationDTO) : ResponseDTO {
        val order = Order(orderCreationDTO)
        DAOManager.factory.orderDAO.push(order)
        return ResponseDTO(data = order)
    }

    fun getOrders(sender: User) : ResponseDTO {
        val orders = DAOManager.factory.orderDAO.filter("user", sender).all
        return ResponseDTO(data = orders)
    }

    fun getOrderById(id: String): ResponseDTO {
        val order = DAOManager.factory.orderDAO.filter("id", id).first
                ?: return ResponseDTO(error = "Invalid id")
        return ResponseDTO(order)
    }

    fun validateOrder(id: String) : ResponseDTO {
        val order = DAOManager.factory.orderDAO.filter("id", id).first
                ?: return ResponseDTO(error = "Invalid id")
        return ResponseDTO(order)
    }

    fun declineOrder(id: String) : ResponseDTO {
        val order = DAOManager.factory.orderDAO.filter("id", id).first
                ?: return ResponseDTO(error = "Invalid id")
        return ResponseDTO()
    }
}
