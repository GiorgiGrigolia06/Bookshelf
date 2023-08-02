package com.example.books.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.books.ui.screens.BooksViewModel
import com.example.books.ui.screens.ResultsScreen
import com.example.books.ui.screens.SearchScreen

enum class BooksScreen {
    SEARCH, RESULTS
}

@Composable
fun BooksApp(
    modifier: Modifier = Modifier
) {
    val booksViewModel: BooksViewModel = viewModel(factory = BooksViewModel.Factory)
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = BooksScreen.valueOf(
        backStackEntry?.destination?.route ?: BooksScreen.SEARCH.name
    )

    NavHost(
        navController = navController,
        startDestination = BooksScreen.SEARCH.name,
        modifier = modifier
    ) {
        composable(route = BooksScreen.SEARCH.name) {
            SearchScreen(
                value = booksViewModel.userInput,
                onValueChange = { booksViewModel.updateUserInput(it) },
                onSearch = {
                    navController.navigate(route = BooksScreen.RESULTS.name)
                    booksViewModel.getBooks()
                }
            )
        }

        composable(route = BooksScreen.RESULTS.name) {
            ResultsScreen(booksUiState = booksViewModel.booksUiState)
        }
    }
}