package com.irene.lyongdsey.persistance.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.irene.lyongdsey.persistance.database.dao.ProjectDao
import com.irene.lyongdsey.persistance.database.dao.StakeholderDao
import com.irene.lyongdsey.persistance.database.entity.ProjectEntity
import com.irene.lyongdsey.persistance.database.entity.StakeholderEntity

const val dbName = "ong_db"

@Database(entities = [ProjectEntity::class, StakeholderEntity::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ONGDataBase : RoomDatabase() {


    abstract fun stakeholderDao(): StakeholderDao
    abstract fun projectDao(): ProjectDao

    companion object
    {
        //SINGLETON
        var INSTANCE: ONGDataBase? = null

        fun getDatabase(context: Context): ONGDataBase? {
            if (INSTANCE == null) {
                synchronized(ONGDataBase::class.java) {
                    if (INSTANCE == null) {
                        context.applicationContext.deleteDatabase(dbName)
                        INSTANCE = Room.databaseBuilder(context.applicationContext,
                            ONGDataBase::class.java, dbName).fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

}