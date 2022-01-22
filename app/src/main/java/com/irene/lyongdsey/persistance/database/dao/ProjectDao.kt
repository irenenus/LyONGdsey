package com.irene.lyongdsey.persistance.database.dao

import androidx.room.*
import com.irene.lyongdsey.persistance.database.entity.ProjectEntity

@Dao
interface ProjectDao {

    @Insert
    fun insert(pInfo: ProjectEntity)

    @Query("SELECT * FROM project")
    fun getAll(): List<ProjectEntity>
}
