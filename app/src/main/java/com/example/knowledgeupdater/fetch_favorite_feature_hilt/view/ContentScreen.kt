package com.example.knowledgeupdater.fetch_favorite_feature_hilt.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.knowledgeupdater.R
import com.example.knowledgeupdater.fetch_favorite_feature_hilt.viewmodel.ContentViewModel

@Composable
fun ContentScreen(viewModel: ContentViewModel) {
    val contentNetworkStateFlow by viewModel.contentFromNetworkStateFlow.collectAsStateWithLifecycle()
    val newestContentDatabaseStateFlow by viewModel.newestContentFromDatabaseStateFlow.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, top = 60.dp, bottom = 16.dp)
    ) {
        Text(
            stringResource(R.string.content_of_network),
            style = MaterialTheme.typography.titleMedium
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            contentNetworkStateFlow.forEach { content ->
                Text("• ${content?.content}", modifier = Modifier.padding(vertical = 4.dp))
            }
        }

        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(onClick = { viewModel.fetchContent() }) { Text(stringResource(R.string.fetch_content)) }
            Button(onClick = { viewModel.saveContentFromNetworkToDatabase() }) {
                Text(
                    stringResource(
                        R.string.favorites
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            stringResource(R.string.showcase_local_favorites),
            style = MaterialTheme.typography.titleMedium
        )
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(newestContentDatabaseStateFlow) { content ->
                Text("• ${content.content}", modifier = Modifier.padding(vertical = 4.dp))
            }
        }
    }
}