package com.speechify.composeuichallenge.ui.bookdetails

import com.speechify.composeuichallenge.data.Book

data class BookDetailsUiState(
    val isLoading: Boolean = false,
    val book: Book?= null,
    val errorMessage: String?= null
)