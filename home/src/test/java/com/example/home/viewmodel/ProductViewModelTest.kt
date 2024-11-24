package com.example.home.viewmodel

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
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

//    @Test
//    fun testGetAllProducts() = runTest {
//        val fakeProducts = listOf(
//            ProductEntity(1L, ItemCategory("Category1")),
//            ProductEntity(2L, ItemCategory("Category2"))
//        )
//
//        // Insert data
//        fakeProducts.forEach { repository.insertCategory(it) }
//
//        viewModel.getallProducts()
//        advanceUntilIdle() // Ensures the coroutine has completed
//
//        val data = viewModel.liveProduct.getOrAwaitValueUnitTest()
//
//        TestCase.assertNotNull(data.data)
//        TestCase.assertEquals(fakeProducts.size, data.data!!.size)
//    }

//    @Test
//    fun testInsertProductId() = runTest {
//        val product = ProductEntity(1L, ItemCategory("NewCategory"))
//        viewModel.insertProductId(product)
//
//        val data = repository.readCategory()
//        TestCase.assertNotNull(data.value)
//    }

    @Test
    fun testInsertCart() = runTest {
        val cartData = CartEntity(
            id = 1,
            cartData = CartData(cartImg = "img.png", carttitle = "Item", cartprice = 100.0)
        )
        viewModel.InsertCart(cartData)

        val data = repository.getProducts()
        TestCase.assertTrue(data.value!!.isNotEmpty())
    }

    @Test
    fun testGetSingleProducts() = runTest {
        val fakeProduct = ProductEntity(1L, ItemCategory("Category1"))
        repository.InsertCategory(fakeProduct)

        viewModel.getSingleProducts("1")
        advanceUntilIdle() // Ensures the coroutine has completed

        val result = viewModel.livesingleProduct.getOrAwaitValueUnitTest()

        println("Result: ${result.data?.category}")
        TestCase.assertNotNull(result.data?.category)
    }
}
