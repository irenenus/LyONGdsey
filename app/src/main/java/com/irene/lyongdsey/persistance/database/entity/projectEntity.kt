package com.irene.lyongdsey.persistance.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "project")
class ProjectEntity(
    @PrimaryKey(autoGenerate = false) val idProject: UUID,
    val name: String,
    val budget: Float,
    val completed: Boolean
)