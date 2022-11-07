package com.example.home.viewmodel

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import junit.framework.TestCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito

@ExperimentalCoroutinesApi
class ProductViewModelTest  {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    val dispatcher = TestCoroutineDispatcher()

    lateinit var viewModel: ProductViewModel
    @Before

    fun setUp(){
        viewModel = ProductViewModel(FakeRepository())
        Dispatchers.setMain(dispatcher)
    }



    fun testGetReadCategory() {}

    fun testSetReadCategory() {}

    fun testGetReadCart() {}

    fun testSetReadCart() {}

    fun testGetLiveProduct() {}

    fun testGetLivesingleProduct() {}

    fun testGetLivecart() {}

    @Test
    fun testGetallProducts() {
        viewModel.getallProducts()
        var data = viewModel.liveProduct.getOrAwaitValueUnitTest()
        TestCase.assertTrue(data.data != null)
    }

    fun testInsertProductId() {}


    fun testInsertCart() {

    }

    @Test
    fun testGetSingleProducts() {
        viewModel.getSingleProducts("2")
        val result = viewModel.livesingleProduct.getOrAwaitValueUnitTest()
        TestCase.assertTrue(result.data != null)


    }
}