package com.weedrop.api.services

import com.weedrop.api.beans.Subscription
import com.weedrop.api.beans.User
import com.weedrop.api.beans.dto.ResponseDTO
import com.weedrop.api.beans.dto.SubscriptionFormDTO
import com.weedrop.api.database.DAOManager

class SubscriptionService {
    fun subscribe(subscriptionFormDTO: SubscriptionFormDTO, sender: User) : ResponseDTO {
        val pkg = DAOManager.factory.subscriptionPackageDAO.filter("id", subscriptionFormDTO.packageId).first
                ?: return ResponseDTO(error = "Invalid package id")
        val subscription = Subscription()
        subscription.user = sender
        subscription.`package` = pkg
        DAOManager.factory.subscriptionDAO.push(subscription)
        return ResponseDTO(data = subscription)
    }

    fun validateSubscription(id: String) : ResponseDTO {
        val subscription = DAOManager.factory.subscriptionDAO.filter("id", id).first
                ?: return ResponseDTO(error = "Invalid subscription id")
        subscription.validated = true
        DAOManager.factory.subscriptionDAO.push(subscription)
        return ResponseDTO(data = subscription)
    }

    fun declineSubscription(id: String) : ResponseDTO {
        val subscription = DAOManager.factory.subscriptionDAO.filter("id", id).first
                ?: return ResponseDTO(error = "Invalid subscription id")
        DAOManager.factory.subscriptionDAO.push(subscription)
        return ResponseDTO(data = subscription)

    }
}
