package com.weedrop.api.beans

import org.bson.types.ObjectId
import org.mongodb.morphia.annotations.Entity
import org.mongodb.morphia.annotations.Id

@Entity(noClassnameStored = true, value = "subscription_packages")
data class SubscriptionPackage(@Id val id : String = ObjectId.get().toString(),
                               var label : String = "",
                               var price : Double = 0.0,
                               var descripton: String = "")
