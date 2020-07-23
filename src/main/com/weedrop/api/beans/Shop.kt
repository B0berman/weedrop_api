package com.weedrop.api.beans

import com.fasterxml.jackson.annotation.JsonIgnore
import com.weedrop.api.beans.dto.ShopCreationDTO
import org.bson.types.ObjectId
import org.mongodb.morphia.annotations.Entity
import org.mongodb.morphia.annotations.Id
import org.mongodb.morphia.annotations.Reference

@Entity(noClassnameStored = true, value = "shops")
data class Shop(@Id val id : String = ObjectId.get().toString(),
                @JsonIgnore @Reference var admin: User = User(),
                var name: String = "",
                var location: Location = Location()) {
    constructor(shopCreationDTO: ShopCreationDTO, admin: User) : this(admin = admin,
            name = shopCreationDTO.name,
            location = shopCreationDTO.location)
}
