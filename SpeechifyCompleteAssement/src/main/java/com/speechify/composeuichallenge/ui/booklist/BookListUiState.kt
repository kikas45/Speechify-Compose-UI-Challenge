package com.speechify.composeuichallenge.ui.booklist

import com.speechify.composeuichallenge.data.Book

data class BookListUiState(
    val isLoading:Boolean= false,
    val books:List<Book> = emptyList(),
    val filteredBooks: List<Book> = emptyList(),
    val searchQuery: String = "",
    val errorMessage: String?= null
)
