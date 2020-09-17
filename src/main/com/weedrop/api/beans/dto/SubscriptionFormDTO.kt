package com.weedrop.api.beans.dto

import org.bson.types.ObjectId
import org.mongodb.morphia.annotations.Entity
import org.mongodb.morphia.annotations.Id
import org.mongodb.morphia.annotations.Reference

@Entity(noClassnameStored = true, value = "subscriptions")
data class SubscriptionFormDTO(val packageId : String = "",
                               var stripeToken: String? = null)
