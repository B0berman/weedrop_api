package com.weedrop.api.database.dao

import com.weedrop.api.beans.SubscriptionPackage
import com.weedrop.api.beans.User
import com.weedrop.api.database.DataBase
import com.weedrop.api.database.MorphiaDAO

class SubscriptionPackageDAO(db: DataBase) : MorphiaDAO<SubscriptionPackage>(db)
