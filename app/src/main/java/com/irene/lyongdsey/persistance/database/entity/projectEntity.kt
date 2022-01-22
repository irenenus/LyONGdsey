package com.irene.lyongdsey.persistance.database.entity

import android.provider.ContactsContract
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "project")
class ProjectEntity(
    @PrimaryKey(autoGenerate = false) val idProject: UUID,
    val name: String,
    val website: String,
    val image: String,
    val budget: Float,
    val completed: Boolean
)