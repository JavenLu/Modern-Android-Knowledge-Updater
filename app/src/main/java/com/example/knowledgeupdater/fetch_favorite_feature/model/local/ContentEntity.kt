package com.example.knowledgeupdater.fetch_favorite_feature.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "content_table")
data class ContentEntity(@PrimaryKey val id: String, val content: String, val author: String)