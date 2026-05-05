package com.example.knowledgeupdater.fetch_favorite_feature_hilt.model.remote

import retrofit2.http.GET

interface ContentApi {
    @GET(value = "random")
    suspend fun getRandoContent(): List<ContentResponseEntity>
}