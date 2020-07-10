package com.weedrop.api.services

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.UserRecord
import com.google.firebase.database.FirebaseDatabase
import com.weedrop.api.beans.User
import com.weedrop.api.beans.dto.ResponseDTO
import com.weedrop.api.beans.dto.SignupDTO
import com.weedrop.api.database.DAOManager

class AccountService {

    fun signup(signupDTO: SignupDTO) : ResponseDTO {
        val user = User(signupDTO)
        val request = UserRecord.CreateRequest().setUid(user.id)
                .setEmail(user.email)
                .setEmailVerified(true)
                .setPassword(signupDTO.password)
                .setDisplayName(user.firstName + " " + user.lastName)
        try {
            FirebaseAuth.getInstance().createUser(request)
            val userParams: MutableMap<String, String> = HashMap()
            userParams["email"] = user.email
            userParams["displayName"] = user.firstName + " " + user.lastName
            FirebaseDatabase.getInstance().getReference("users").child(user.id).setValueAsync(userParams)
        }catch (  e: FirebaseAuthException){
            e.printStackTrace()
            return ResponseDTO(error = e)
        }
        DAOManager.factory.userDAO.push(user)
        return ResponseDTO(data = user)
    }

}
