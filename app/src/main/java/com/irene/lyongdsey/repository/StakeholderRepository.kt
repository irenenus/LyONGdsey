package com.irene.lyongdsey.repository

import android.app.Application
import com.irene.lyongdsey.persistance.database.ONGDataBase
import com.irene.lyongdsey.persistance.database.entity.StakeholderEntity

class StakeholderRepository(application: Application) {

    var database: ONGDataBase? = null

    init {
        database = ONGDataBase.getDatabase(application)
    }
    // insert data to db
    fun addStakeholder(stakeholderEntity: StakeholderEntity){
        database?.stakeholderDao()?.insert(stakeholderEntity)
    }
    // insert ask the db through a query to get all the stakeholders
    fun getAllStakeholders() : List<StakeholderEntity>? {
        return database?.stakeholderDao()?.getAll()
    }


}