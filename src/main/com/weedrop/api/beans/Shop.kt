package com.weedrop.api.beans

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude
import com.weedrop.api.beans.dto.ShopCreationDTO
import org.bson.types.ObjectId
import org.mongodb.morphia.annotations.Entity
import org.mongodb.morphia.annotations.Id
import org.mongodb.morphia.annotations.Reference

@Entity(noClassnameStored = true, value = "shops")
@JsonInclude(JsonInclude.Include.NON_NULL)
data class Shop(@Id val id : String = ObjectId.get().toString(),
                @JsonIgnore @Reference var admin: User = User(),
                var name: String = "",
                var location: Location = Location(),
                var isActive: Boolean = false,
                var isVisible: Boolean = false) {
    constructor(shopCreationDTO: ShopCreationDTO, admin: User) : this(admin = admin,
            name = shopCreationDTO.name,
            location = shopCreationDTO.location)
}
