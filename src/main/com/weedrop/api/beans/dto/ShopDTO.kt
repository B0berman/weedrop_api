package com.weedrop.api.beans.dto

import com.weedrop.api.beans.Location
import com.weedrop.api.beans.Product
import com.weedrop.api.beans.Shop
import org.bson.types.ObjectId

data class ShopDTO(val id : String = ObjectId.get().toString(),
                   var name: String = "",
                   var location: Location = Location(),
                   var products: List<Product> = emptyList()) {
    constructor(shop: Shop, products: List<Product>) : this(name = shop.name,
            location = shop.location,
            id = shop.id,
            products = products)
}
