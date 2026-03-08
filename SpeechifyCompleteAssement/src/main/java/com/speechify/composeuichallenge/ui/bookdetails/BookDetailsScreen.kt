package com.speechify.composeuichallenge.ui.bookdetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.speechify.composeuichallenge.ui.anim.SharedElementImage

@Composable
fun BookDetailsScreen(
    bookId: String,
    viewModel: BookDetailsViewModel = hiltViewModel()
) {

    val state by viewModel.uiState.collectAsState()

    //load book when screen enters
    if (state.book == null && !state.isLoading) {
        viewModel.loadBook(bookId)
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Top))
            .padding(horizontal = 16.dp)
    ) {


        when{
            state.isLoading ->{
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            state.errorMessage != null->{
                Text(
                    text = state.errorMessage?:"",
                    color = MaterialTheme.colorScheme.error
                )
            }
            else->{
                state.book?.let { book ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        SharedElementImage(targetKey = book.id) {
                            AsyncImage(
                                model = book.imageUrl,
                                contentDescription = book.name,
                                modifier = Modifier.aspectRatio(1f),
                                contentScale = ContentScale.Crop
                            )
                        }
                        Text(
                            text = book.name,
                            style = MaterialTheme.typography.titleMedium
                        )

                        Text(
                            text = "by ${book.author}",
                            style = MaterialTheme.typography.labelMedium
                        )


                        Text(
                            text = "Rating: ${book.rating} (${book.reviewCount})",
                            style = MaterialTheme.typography.bodyMedium
                        )

                        Text(
                            text = book.description,
                            style = MaterialTheme.typography.bodyMedium
                        )

                    }
                }
            }
        }

    }

}