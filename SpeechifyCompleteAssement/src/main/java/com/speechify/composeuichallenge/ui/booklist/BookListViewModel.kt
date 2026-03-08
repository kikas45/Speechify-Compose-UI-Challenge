package com.speechify.composeuichallenge.ui.booklist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.speechify.composeuichallenge.repository.BooksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import okio.IOException

@HiltViewModel
class BookListViewModel @Inject constructor(
    private val repository: BooksRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(BookListUiState())
    val uiState: StateFlow<BookListUiState> = _uiState


    init {
        loadBooks()
    }

    private fun loadBooks() {
        viewModelScope.launch {

            _uiState.update { it.copy(isLoading = true, errorMessage = null) }

            try {
                val books = repository.getBooks()

                _uiState.update {
                    it.copy(
                        isLoading = false,
                        books = books,
                        filteredBooks = books
                    )
                }

            } catch (e: IOException) {

                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = "Failed to load books. Please try again."
                    )
                }

            } catch (e: Exception) {

                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = "Unexpected error occurred."
                    )
                }

            }
        }
    }

    fun onSearch(query: String) {

        _uiState.update {
            it.copy(
                searchQuery = query,
                errorMessage = null
            )
        }

        viewModelScope.launch {

            try {

                val currentBooks = _uiState.value.books

                val result =
                    if (query.isBlank()) currentBooks
                    else repository.searchBook(query)

                _uiState.update {
                    it.copy(filteredBooks = result)
                }

            } catch (e: IOException) {

                _uiState.update {
                    it.copy(errorMessage = "Search failed. Please try again")
                }

            } catch (e: Exception) {

                _uiState.update {
                    it.copy(errorMessage = "Unexpected error during search.")
                }

            }

        }
    }
}