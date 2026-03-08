package com.speechify.composeuichallenge.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.speechify.composeuichallenge.ui.booklist.BookListScreen
import com.speechify.composeuichallenge.navigation.Screen
import com.speechify.composeuichallenge.ui.bookdetails.BookDetailsScreen


@Composable
fun AppNavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.BookList.route
    ) {

        composable(Screen.BookList.route) {
            BookListScreen(
                onBookClick = { bookId ->
                    navController.navigate(
                        Screen.BookDetails.createRoute(bookId)
                    )
                }
            )
        }

        composable(
            route = Screen.BookDetails.route,
            arguments = listOf(
                navArgument(Screen.BookDetails.ARG_BOOK_ID) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->

            val bookId = backStackEntry.arguments
                ?.getString(Screen.BookDetails.ARG_BOOK_ID)
                ?: ""

            BookDetailsScreen(bookId = bookId)
        }
    }
}
