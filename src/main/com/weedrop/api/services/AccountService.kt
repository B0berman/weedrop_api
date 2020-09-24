package com.weedrop.api.services

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.UserRecord
import com.google.firebase.database.FirebaseDatabase
import com.weedrop.api.beans.User
import com.weedrop.api.beans.dto.CodeValidationDTO
import com.weedrop.api.beans.dto.ResponseDTO
import com.weedrop.api.beans.dto.SignupDTO
import com.weedrop.api.database.DAOManager
import com.weedrop.api.helpers.RandomString
import com.weedrop.api.helpers.encryptCode
import com.weedrop.api.helpers.isValidCode
import com.weedrop.api.helpers.sendEmail

class AccountService {

    fun signup(signupDTO: SignupDTO) : ResponseDTO {
        val user = User(signupDTO)
        if (!user.email.matches(Regex("(?:[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])"))) {
            return ResponseDTO(error = "Invalid email")
        }
        val code: String = RandomString().nextString()
        val validationCode = encryptCode(this.javaClass, code, user.email)
        val subject = "Code de validation"
        val message = "Utilisez le lien suivant pour valider votre compte : <!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Weedrop validation email</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <a href=\"https://wd-front.herokuapp.com/verify?code=$validationCode\">LINK</a>\n" +
                "</body>\n" +
                "</html>"
        DAOManager.factory.userDAO.push(user)
        if (!sendEmail(user.email, subject, message, null, this.javaClass))
            return ResponseDTO(error = "Error sending email.")
        return ResponseDTO(data = user)
    }

    fun validateAccount(validationDTO: CodeValidationDTO) : ResponseDTO {
        if (validationDTO.code.isEmpty() || validationDTO.password.isEmpty())
            return ResponseDTO(error = "All fields must be filled.")
        val user = isValidCode(validationDTO.code, this.javaClass)
        return if (user != null) {
            val request = UserRecord.CreateRequest().setUid(user.id)
                    .setEmail(user.email)
                    .setEmailVerified(true)
                    .setPassword(validationDTO.password)
                    .setDisplayName(user.firstName + " " + user.lastName)
            try {
                FirebaseAuth.getInstance().createUser(request)
                val userParams: MutableMap<String, String> = HashMap()
                userParams["email"] = user.email
                userParams["displayName"] = user.firstName + " " + user.lastName
                FirebaseDatabase.getInstance().getReference("users").child(user.id).setValueAsync(userParams)
                user.validated = true
                DAOManager.factory.userDAO.push(user)
            }catch (  e: FirebaseAuthException){
                e.printStackTrace()
                ResponseDTO(error = "Couldn't create user. Please verify informations")
            }
            ResponseDTO(data = user)
        } else {
            ResponseDTO(error = "Invalid code")
        }
    }
}
