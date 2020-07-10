package com.weedrop.api.database

import com.mongodb.MongoClient
import com.mongodb.MongoClientURI
import com.weedrop.api.helpers.getProperty
import org.mongodb.morphia.Datastore
import org.mongodb.morphia.Morphia


class MorphiaDB : DataBase {

    private var morphia: Morphia = Morphia()
    private var client: MongoClient? = null
    var datastore: Datastore? = null

    private val url = "188.165.243.185"

    override fun connect(): Boolean {

        val user = getProperty("db_user", this::class.java, "keys")
        val pwd = getProperty("db_pwd", this::class.java, "keys")
        val uri = MongoClientURI(
                "mongodb://$user:$pwd@188.165.243.185/weedropDB?retryWrites=true&w=majority")

        client = MongoClient(uri)
     //   val database = client!!.getDatabase("weedropDB")
        morphia = Morphia()

        datastore = morphia.createDatastore(client!!, getProperty("db_name", this::class.java, "keys"))
        return true
    }

    override fun disconnect(): Boolean {
        client!!.close()
        return true
    }

}
