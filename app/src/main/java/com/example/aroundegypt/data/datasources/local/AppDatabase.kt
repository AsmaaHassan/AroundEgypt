package com.example.aroundegypt.data.datasources.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.aroundegypt.data.datasources.local.dao.ExperienceDao
import com.example.aroundegypt.data.datasources.local.entity.ExperienceEntity

@Database(entities = [ExperienceEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun experienceDao(): ExperienceDao
}
