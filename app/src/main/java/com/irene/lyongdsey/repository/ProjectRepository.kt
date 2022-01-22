package com.irene.lyongdsey.repository

import android.app.Application
import com.irene.lyongdsey.persistance.database.ONGDataBase
import com.irene.lyongdsey.persistance.database.entity.ProjectEntity

class ProjectRepository(application: Application) {

    var database: ONGDataBase? = null

    init {
        database = ONGDataBase.getDatabase(application)
    }

    fun addProject(projectEntity: ProjectEntity){
        database?.projectDao()?.insert(projectEntity)
    }

    fun getAllProjects() : List<ProjectEntity>? {
        return database?.projectDao()?.getAll()
    }


}