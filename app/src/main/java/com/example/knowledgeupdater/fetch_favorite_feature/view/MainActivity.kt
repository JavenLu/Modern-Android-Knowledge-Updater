package com.example.knowledgeupdater.fetch_favorite_feature.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.example.knowledgeupdater.fetch_favorite_feature.KnowledgeUpdaterApplication
import com.example.knowledgeupdater.fetch_favorite_feature.model.repository.ContentRepository
import com.example.knowledgeupdater.fetch_favorite_feature.viewmodel.ContentViewModel
import com.example.knowledgeupdater.fetch_favorite_feature.viewmodel.ContentViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repository = ContentRepository(
            KnowledgeUpdaterApplication.instance.dao,
            KnowledgeUpdaterApplication.instance.api
        )

        val factory = ContentViewModelFactory(repository)
        val viewModel = ViewModelProvider(this, factory)[ContentViewModel::class.java]

        setContent {
            ContentScreen(viewModel)
        }
    }
}