package com.irene.lyongdsey.persistance.database.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Entity(tableName = "stakeholder")
@Parcelize
class StakeholderEntity(
    @PrimaryKey(autoGenerate = false) val idStakeholder: UUID,
    val name: String,
   // val projectsFounded: List<String>,
    val amount: Float
) : Parcelable