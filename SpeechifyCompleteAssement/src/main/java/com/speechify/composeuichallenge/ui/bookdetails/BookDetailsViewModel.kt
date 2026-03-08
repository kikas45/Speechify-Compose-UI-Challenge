package com.speechify.composeuichallenge.ui.bookdetails

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.speechify.composeuichallenge.repository.BooksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch
import okio.IOException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class BookDetailsViewModel @Inject constructor(
    private val repository: BooksRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(BookDetailsUiState())
    val uiState: StateFlow<BookDetailsUiState> = _uiState

    fun loadBook(id: String) {

        viewModelScope.launch {

            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            try {

                val book = repository.getBook(id)

                if (book != null) {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            book = book
                        )
                    }

                } else {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = "Book not found."
                        )
                    }

                }

            } catch (e: IOException) {

                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = "Failed to load book"
                    )
                }

            } catch (e: Exception) {

                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = "Unexpected error occurred"
                    )
                }

            }
        }
    }
}


