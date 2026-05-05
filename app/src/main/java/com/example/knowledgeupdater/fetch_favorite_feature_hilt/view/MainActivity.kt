package com.example.knowledgeupdater.fetch_favorite_feature_hilt.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.knowledgeupdater.fetch_favorite_feature_hilt.viewmodel.ContentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val viewModel: ContentViewModel = hiltViewModel()
            ContentScreen(viewModel)
        }
    }
}