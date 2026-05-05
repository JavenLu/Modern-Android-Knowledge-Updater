package com.example.knowledgeupdater.fetch_favorite_feature_hilt.model.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ContentEntityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContent(quote: ContentEntity)

    @Query("SELECT * FROM content_table ORDER BY id ASC")
    fun getAllContentFlow(): Flow<List<ContentEntity>>
}