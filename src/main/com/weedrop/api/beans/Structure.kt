package com.weedrop.api.beans

import org.bson.types.ObjectId
import org.mongodb.morphia.annotations.Entity
import org.mongodb.morphia.annotations.Id

@Entity(noClassnameStored = true, value = "shops")
data class Structure(@Id val id : String = ObjectId.get().toString(),
                     var name: String = "")
