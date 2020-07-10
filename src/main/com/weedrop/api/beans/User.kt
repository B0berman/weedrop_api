package com.weedrop.api.beans

import com.fasterxml.jackson.annotation.JsonIgnore
import com.weedrop.api.beans.dto.SignupDTO
import org.bson.types.ObjectId
import org.mongodb.morphia.annotations.Entity
import org.mongodb.morphia.annotations.Id

@Entity(noClassnameStored = true, value = "users")
data class User(@Id val id : String = ObjectId.get().toString(),
                var email: String = "",
                var firstName: String = "",
                var lastName: String = "",
                @JsonIgnore val validated: Boolean = true) {

    constructor(signupDTO: SignupDTO) : this(email = signupDTO.email,
            firstName = signupDTO.firstName,
            lastName = signupDTO.lastName)
}
