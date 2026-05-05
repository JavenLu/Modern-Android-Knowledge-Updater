package com.example.knowledgeupdater.fetch_favorite_feature_hilt.model.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ContentEntity::class], version = 1, exportSchema = false)
abstract class ContentDatabase : RoomDatabase() {
    abstract fun contentDao(): ContentEntityDao
}