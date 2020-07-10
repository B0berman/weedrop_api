package com.weedrop.api.beans.dto

import com.weedrop.api.beans.Product

data class OrderCreationDTO(var userId: String = "",
                            var products : List<Product> = emptyList())
