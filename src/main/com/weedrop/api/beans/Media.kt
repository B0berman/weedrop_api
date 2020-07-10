package com.weedrop.api.beans

import org.bson.types.ObjectId
import org.mongodb.morphia.annotations.Entity
import org.mongodb.morphia.annotations.Id

@Entity(noClassnameStored = true, value = "users")
data class Media(@Id val id : String = ObjectId.get().toString(),
                 var url: String = "")
