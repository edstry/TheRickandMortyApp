@file:OptIn(ExperimentalMaterial3Api::class)

package com.edstry.therickandmortyapp.presentation.characters

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil3.compose.AsyncImage

@Composable
fun CharactersScreen(
    vm: CharactersViewModel = hiltViewModel()
) {
    val pagingItems = vm.characters.collectAsLazyPagingItems()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Rick & Morty") }) }
    ) { padding ->
        Box(Modifier
            .fillMaxSize()
            .padding(padding)) {

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(
                    count = pagingItems.itemCount,
                    key = { index -> pagingItems[index]?.id ?: "placeholder_$index" }
                ) { index ->
                    val item = pagingItems[index] ?: return@items
                    CharacterRow(
                        name = item.name,
                        meta = "${item.status} • ${item.species} • ${item.gender}",
                        imageUrl = item.image
                    )
                }

                // Footer states
                item {
                    when (val append = pagingItems.loadState.append) {
                        is LoadState.Loading -> {
                            Row(
                                Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) { CircularProgressIndicator() }
                        }

                        is LoadState.Error -> {
                            ErrorBlock(
                                message = append.error.message ?: "Ошибка загрузки",
                                onRetry = { pagingItems.retry() }
                            )
                        }

                        else -> Unit
                    }
                }
            }

            // Initial load states
            when (val refresh = pagingItems.loadState.refresh) {
                is LoadState.Loading -> {
                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                }

                is LoadState.Error -> {
                    ErrorBlock(
                        modifier = Modifier.align(Alignment.Center),
                        message = refresh.error.message ?: "Ошибка",
                        onRetry = { pagingItems.retry() }
                    )
                }

                else -> Unit
            }
        }
    }
}

@Composable
private fun CharacterRow(name: String, meta: String, imageUrl: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        shape = RoundedCornerShape(5.dp)
    ) {
        Row(
            Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = name,
                modifier = Modifier
                    .clip(RoundedCornerShape(
                        topStart = 5.dp,
                        bottomStart = 5.dp)
                    )
            )
            Spacer(Modifier.width(12.dp))
            Column {
                Text(name, style = MaterialTheme.typography.titleMedium)
                Spacer(Modifier.height(4.dp))
                Text(meta, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

@Composable
private fun ErrorBlock(
    message: String,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(message)
        Spacer(Modifier.height(8.dp))
        Button(onClick = onRetry) { Text("Повторить") }
    }

}