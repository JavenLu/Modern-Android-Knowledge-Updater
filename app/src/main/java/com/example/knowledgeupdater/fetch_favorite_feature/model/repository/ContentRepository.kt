package com.example.knowledgeupdater.fetch_favorite_feature.model.repository

import com.example.knowledgeupdater.fetch_favorite_feature.model.local.ContentEntity
import com.example.knowledgeupdater.fetch_favorite_feature.model.local.ContentEntityDao
import com.example.knowledgeupdater.fetch_favorite_feature.model.remote.ContentApi
import com.example.knowledgeupdater.fetch_favorite_feature.model.remote.ContentResponseEntity
import kotlinx.coroutines.flow.Flow

class ContentRepository(private val dao: ContentEntityDao, private val api: ContentApi) {

    val getContentFlow: Flow<List<ContentEntity>> = dao.getAllContentFlow()

    suspend fun saveContent(content: ContentEntity) {
        dao.insertContent(content)
    }

    suspend fun fetchContent(): List<ContentResponseEntity> {
        return api.getRandoContent()
    }


}