package com.speechify.composeuichallenge.navigation


sealed class Screen(val route: String) {

    data object BookList : Screen("book_list")

    data object BookDetails : Screen("book_details/{bookId}") {

        const val ARG_BOOK_ID = "bookId"

        fun createRoute(bookId: String): String {
            return "book_details/$bookId"
        }
    }
}