package com.example.knowledgeupdater.fetch_favorite_feature

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.knowledgeupdater.fetch_favorite_feature.model.local.ContentDatabase
import com.example.knowledgeupdater.fetch_favorite_feature.model.local.ContentEntityDao
import com.example.knowledgeupdater.fetch_favorite_feature.model.remote.ContentApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class KnowledgeUpdaterApplication : Application() {
    companion object {
        private var _instance: KnowledgeUpdaterApplication? = null
        val instance: KnowledgeUpdaterApplication get() = _instance!!
    }

    lateinit var dao: ContentEntityDao
    lateinit var api: ContentApi

    override fun onCreate() {
        super.onCreate()
        _instance = this

        val database = Room.databaseBuilder(
            applicationContext,
            ContentDatabase::class.java,
            "content_database_db"
        ).build()
        dao = database.contentDao()
        api = Retrofit.Builder().baseUrl("https://69e21f55b1cb62b9f317cbb7.mockapi.io")
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(ContentApi::class.java)
    }


}