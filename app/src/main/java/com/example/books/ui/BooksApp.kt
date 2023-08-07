package com.example.books.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.books.R
import com.example.books.ui.screens.BooksViewModel
import com.example.books.ui.screens.ResultsScreen
import com.example.books.ui.screens.SearchScreen

enum class BooksScreen {
    SEARCH, RESULTS
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BooksApp(
    modifier: Modifier = Modifier
) {
    val booksViewModel: BooksViewModel = viewModel(factory = BooksViewModel.Factory)
    val navController = rememberNavController()

    Scaffold(
        topBar = { BooksAppTopAppBar() }
    ) { it ->
        Surface(
            modifier = modifier
                .fillMaxSize()
                .padding(it)
        ) {
            NavHost(
                navController = navController,
                startDestination = BooksScreen.SEARCH.name,
            ) {
                composable(route = BooksScreen.SEARCH.name) {
                    SearchScreen(
                        value = booksViewModel.userInput,
                        onValueChange = { booksViewModel.updateUserInput(it) },
                        onSearch = {
                            navController.navigate(route = BooksScreen.RESULTS.name)
                            booksViewModel.getBooks()
                        },
                        clearUserInput = { booksViewModel.clearUserInput() }
                    )
                }

                composable(route = BooksScreen.RESULTS.name) {
                    ResultsScreen(
                        booksUiState = booksViewModel.booksUiState,
                        tryAgain = { booksViewModel.getBooks() },
                        goHome = { navController.navigate(route = BooksScreen.SEARCH.name) }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BooksAppTopAppBar(
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.app_name).uppercase(),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.87f)
            )
        },
        modifier = modifier
    )
}