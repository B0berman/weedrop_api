package com.weedrop.api.database.dao

import com.weedrop.api.beans.Shop
import com.weedrop.api.database.DataBase
import com.weedrop.api.database.MorphiaDAO

class ShopDAO(db: DataBase) : MorphiaDAO<Shop>(db)
