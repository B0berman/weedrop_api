package com.weedrop.api.beans

import org.bson.types.ObjectId
import org.mongodb.morphia.annotations.Entity
import org.mongodb.morphia.annotations.Id
import org.mongodb.morphia.annotations.Reference

@Entity(noClassnameStored = true, value = "subscriptions")
data class Subscription(@Id val id : String = ObjectId.get().toString(),
                        @Reference val `package` : SubscriptionPackage = SubscriptionPackage(),
                        @Reference var user: User = User(),
                        var stripeToken: String? = null,
                        var startTime: Long = 0,
                        var endTime: Long = 0)
