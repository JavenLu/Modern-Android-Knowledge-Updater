package com.example.knowledgeupdater.fetch_favorite_feature_hilt.model.hilt

import android.content.Context
import androidx.room.Room
import com.example.knowledgeupdater.fetch_favorite_feature_hilt.model.local.ContentDatabase
import com.example.knowledgeupdater.fetch_favorite_feature_hilt.model.local.ContentEntityDao
import com.example.knowledgeupdater.fetch_favorite_feature_hilt.model.remote.ContentApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideContentApi(): ContentApi {
        return Retrofit.Builder().baseUrl("https://69e21f55b1cb62b9f317cbb7.mockapi.io")
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(ContentApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): ContentDatabase {
        return Room.databaseBuilder(
            context,
            ContentDatabase::class.java,
            "content_database_db"
        ).build()
    }

    @Provides
    fun provideContentDao(db: ContentDatabase): ContentEntityDao = db.contentDao()
}