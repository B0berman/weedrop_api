package com.weedrop.api.beans

import com.weedrop.api.beans.dto.ProductCreationDTO
import org.bson.types.ObjectId
import org.mongodb.morphia.annotations.Entity
import org.mongodb.morphia.annotations.Id
import org.mongodb.morphia.annotations.Reference

@Entity(noClassnameStored = true, value = "products")
data class Product(@Id val id : String = ObjectId.get().toString(),
                   @Reference var shop: Shop = Shop(),
                   var title: String = "",
                   var price: Double = 0.0,
                   var description: String = "") {
    constructor(productCreationDTO: ProductCreationDTO) : this(title = productCreationDTO.title,
            description = productCreationDTO.description,
            price = productCreationDTO.price) {

    }
}
