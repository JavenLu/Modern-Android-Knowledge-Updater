package com.example.knowledgeupdater.fetch_favorite_feature_hilt.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.knowledgeupdater.fetch_favorite_feature_hilt.model.local.ContentEntity
import com.example.knowledgeupdater.fetch_favorite_feature_hilt.model.remote.ContentResponseEntity
import com.example.knowledgeupdater.fetch_favorite_feature_hilt.model.repository.ContentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class ContentViewModel @Inject constructor(private val repository: ContentRepository) :
    ViewModel() {
    private val _content = MutableStateFlow<List<ContentResponseEntity>>(emptyList())
    val contentFromNetworkStateFlow: StateFlow<List<ContentResponseEntity>> = _content.asStateFlow()

    fun fetchContent() {
        viewModelScope.launch {
            try {
                _content.value = repository.fetchContent()
            } catch (e: Exception) {
                _content.value = emptyList()
            }
        }
    }

    val newestContentFromDatabaseStateFlow: StateFlow<List<ContentEntity>> =
        repository.getContentFlow.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun saveContentFromNetworkToDatabase() {
        contentFromNetworkStateFlow.value.let { list ->
            if (list.isNotEmpty()) {
                viewModelScope.launch {
                    list.forEach {
                        repository.saveContent(ContentEntity(it._id, it.content, it.author))
                    }
                }

            }

        }
    }


}