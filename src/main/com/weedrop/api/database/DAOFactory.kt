package com.weedrop.api.database

import com.weedrop.api.beans.*

abstract class DAOFactory(protected var db: DataBase) {

    abstract val userDAO: DAO<User>
    abstract val orderDAO: DAO<Order>
    abstract val productDAO: DAO<Product>
    abstract val subscriptionPackageDAO: DAO<SubscriptionPackage>
    abstract val subscriptionDAO: DAO<Subscription>
    abstract val shopDAO: DAO<Shop>
}
