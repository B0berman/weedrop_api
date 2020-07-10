package com.weedrop.api.beans.dto

import com.weedrop.api.beans.Location


data class ShopCreationDTO(val name: String = "",
                           val location: Location = Location())
