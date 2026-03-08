package com.speechify.composeuichallenge.ui.booklist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel

@Composable
fun BookListScreen(
    onBookClick: (String) -> Unit,
    viewModel: BookListViewModel = hiltViewModel()

) {
    val state by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Top))
            .padding(horizontal = 16.dp)
    ) {

        SearchBar(
            query = state.searchQuery,
            onQueryChange = viewModel::onSearch
        )

        when {
            state.isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center

                ) {
                    CircularProgressIndicator()
                }
            }

            state.errorMessage != null -> {

                Box(contentAlignment = Alignment.Center) {
                    Text(
                        text = state.errorMessage?:"",
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }

            else -> {
                LazyColumn {
                    items(items = state.filteredBooks, key = { it.id }) { book ->

                        BookItem(
                            book = book,
                            modifier = Modifier.animateItem(),
                            onDetailsClick = {
                                onBookClick(book.id)
                            }
                        )
                    }
                }
            }
        }

    }

}