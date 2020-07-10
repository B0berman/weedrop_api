package com.weedrop.api.database.dao

import com.weedrop.api.beans.Subscription
import com.weedrop.api.beans.User
import com.weedrop.api.database.DataBase
import com.weedrop.api.database.MorphiaDAO

class SubscriptionDAO(db: DataBase) : MorphiaDAO<Subscription>(db)
