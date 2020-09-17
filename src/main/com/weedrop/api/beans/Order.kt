package com.weedrop.api.beans

import com.weedrop.api.beans.dto.OrderCreationDTO
import org.bson.types.ObjectId
import org.mongodb.morphia.annotations.Entity
import org.mongodb.morphia.annotations.Id
import org.mongodb.morphia.annotations.Reference

@Entity(noClassnameStored = true, value = "orders")
data class Order(@Id val id : String = ObjectId.get().toString(),
                 @Reference val user: User = User(),
                 var status: String = "",
                 var type: String = "",
                 var products: List<Product> = emptyList(),
                 var destination: Location = Location()) {
    constructor(orderCreationDTO: OrderCreationDTO) : this(status = "PAYMENT_PENDING",
            type = "",
            products = orderCreationDTO.products)
}
