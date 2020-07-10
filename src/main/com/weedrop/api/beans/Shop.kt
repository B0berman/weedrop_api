package com.weedrop.api.beans

import com.weedrop.api.beans.dto.ShopCreationDTO
import org.bson.types.ObjectId
import org.mongodb.morphia.annotations.Entity
import org.mongodb.morphia.annotations.Id
import org.mongodb.morphia.annotations.Reference

@Entity(noClassnameStored = true, value = "shops")
data class Shop(@Id val id : String = ObjectId.get().toString(),
                @Reference var admin: User = User(),
                var name: String = "",
                var locations: Location = Location()) {
    constructor(shopCreationDTO: ShopCreationDTO, admin: User) : this(admin = admin,
            name = shopCreationDTO.name,
            locations = shopCreationDTO.location)
}
