package com.example.home.viewmodel

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.data.room.CartData
import com.example.data.room.CartEntity
import com.example.data.room.ItemCategory
import com.example.data.room.ProductEntity
import junit.framework.TestCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class ProductViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private val dispatcher = StandardTestDispatcher()

    private lateinit var viewModel: ProductViewModel
    private lateinit var repository: FakeRepository

    @Before
    fun setUp() {
        repository = FakeRepository()
        viewModel = ProductViewModel(repository)
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun testGetAllProducts() = runTest {
        val fakeProducts = listOf(
            ProductEntity(1L, ItemCategory("Category1")),
            ProductEntity(2L, ItemCategory("Category2"))
        )


        fakeProducts.forEach { repository.InsertCategory(it) }


        advanceUntilIdle()
        repository.productEntity.value = ProductEntity(1L, ItemCategory("NewCategory"))

        val data = repository.ReadCategory().getOrAwaitValueUnitTest()
        TestCase.assertNotNull(data)

        println("TESTDATA: "+data)
    }

    @Test
    fun testInsertProductId() = runTest {
        repository.productEntity.value = ProductEntity(1L, ItemCategory("NewCategory"))

        val data = repository.ReadCategory()
        TestCase.assertNotNull(data.value)
    }



}
