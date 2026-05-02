package com.example.knowledgeupdater.fetch_favorite_feature.model.remote

import retrofit2.http.GET

interface ContentApi {
    @GET(value = "random")
    suspend fun getRandoContent(): List<ContentResponseEntity>
}