package com.example.books.fake

import com.example.books.rules.TestDispatcherRule
import com.example.books.ui.screens.BooksUiState
import com.example.books.ui.screens.BooksViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class ViewModelTest {
    @get:Rule
    val testDispatcher = TestDispatcherRule()
    @Test
    fun viewModel_getBooks_verifyUiStateSuccess() =
        runTest {
            val viewModel = BooksViewModel(
                booksRepository = FakeNetworkRepository()
            )

            viewModel.getBooks()

            assertEquals(
                BooksUiState.Success(FakeDataSource.apiResponse),
                viewModel.booksUiState
            )
        }
}
