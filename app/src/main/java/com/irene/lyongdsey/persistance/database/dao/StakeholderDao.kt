package com.irene.lyongdsey.persistance.database.dao

import androidx.room.*
import com.irene.lyongdsey.persistance.database.entity.ProjectEntity
import com.irene.lyongdsey.persistance.database.entity.StakeholderEntity

@Dao
interface StakeholderDao {

    @Insert
    fun insert(pInfo: StakeholderEntity)

    @Query("SELECT * FROM stakeholder")
    fun getAll(): List<StakeholderEntity>
}
