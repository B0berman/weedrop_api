package com.weedrop.api.services

import com.weedrop.api.beans.dto.ResponseDTO
import com.weedrop.api.database.DAOManager

class UserService {

    fun getUserById(id: String) : ResponseDTO {
        val user = DAOManager.factory.userDAO.filter("id", id).first
                ?: return ResponseDTO(error = "Invalid id")
        return ResponseDTO(data = user)
    }
}
