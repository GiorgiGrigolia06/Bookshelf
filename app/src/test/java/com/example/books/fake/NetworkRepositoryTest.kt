package com.example.books.fake

import com.example.books.data.NetworkRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test

class NetworkRepositoryTest {
    @Test
    fun networkRepository_getBooks_verifyItemList() =
        runTest {
            val repository = NetworkRepository(
                apiService = FakeApiService()
            )

            assertEquals(FakeDataSource.apiResponse, repository.getBooks("fakeQuery"))
        }

}
