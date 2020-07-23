package com.weedrop.api.beans

import com.fasterxml.jackson.annotation.JsonInclude
import com.weedrop.api.beans.dto.ProductCreationDTO
import org.bson.types.ObjectId
import org.mongodb.morphia.annotations.Entity
import org.mongodb.morphia.annotations.Id
import org.mongodb.morphia.annotations.Reference

@Entity(noClassnameStored = true, value = "products")
@JsonInclude(JsonInclude.Include.NON_NULL)
data class Product(@Id val id : String = ObjectId.get().toString(),
                   @Reference var shop: Shop? = Shop(),
                   var title: String = "",
                   var price: Double = 0.0,
                   var description: String = "",
                   var quantity: Int = 0) {
    constructor(productCreationDTO: ProductCreationDTO, shop: Shop) : this(title = productCreationDTO.title,
            shop = shop,
            description = productCreationDTO.description,
            price = productCreationDTO.price,
            quantity = productCreationDTO.quantity) {

    }
}
