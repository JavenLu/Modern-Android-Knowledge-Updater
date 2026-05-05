package com.example.knowledgeupdater.fetch_favorite_feature_hilt.model.repository

import com.example.knowledgeupdater.fetch_favorite_feature_hilt.model.local.ContentEntity
import com.example.knowledgeupdater.fetch_favorite_feature_hilt.model.local.ContentEntityDao
import com.example.knowledgeupdater.fetch_favorite_feature_hilt.model.remote.ContentApi
import com.example.knowledgeupdater.fetch_favorite_feature_hilt.model.remote.ContentResponseEntity
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class ContentRepository @Inject constructor(private val dao: ContentEntityDao, private val api: ContentApi) {

    val getContentFlow: Flow<List<ContentEntity>> = dao.getAllContentFlow()

    suspend fun saveContent(content: ContentEntity) {
        dao.insertContent(content)
    }

    suspend fun fetchContent(): List<ContentResponseEntity> {
        return api.getRandoContent()
    }


}