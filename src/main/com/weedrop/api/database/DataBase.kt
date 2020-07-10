package com.weedrop.api.database

interface DataBase {

    fun connect(): Boolean

    fun disconnect(): Boolean
}
