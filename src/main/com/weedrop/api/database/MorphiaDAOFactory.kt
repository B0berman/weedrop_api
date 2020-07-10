package com.weedrop.api.database

import com.weedrop.api.beans.*
import com.weedrop.api.database.dao.*

class MorphiaDAOFactory(db: MorphiaDB) : DAOFactory(db) {

    override val userDAO: DAO<User>
        get() = UserDAO(db)

    override val orderDAO: DAO<Order>
        get() = OrderDAO(db)

    override val productDAO: DAO<Product>
        get() = ProductDAO(db)

    override val subscriptionPackageDAO: DAO<SubscriptionPackage>
        get() = SubscriptionPackageDAO(db)

    override val subscriptionDAO: DAO<Subscription>
        get() = SubscriptionDAO(db)

    override val shopDAO: DAO<Shop>
        get() = ShopDAO(db)

}
