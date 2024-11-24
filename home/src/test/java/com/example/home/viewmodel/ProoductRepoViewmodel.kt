package com.example.home.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.common.NetworkCall
import com.example.data.model.product_model.Products
import com.example.data.room.ItemCategory
import com.example.home.room.ProductEntity
import com.example.network.service.ProductRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ProductViewModelTests {

    @Mock
    private lateinit var repository: ProductRepository

    private lateinit var viewModel: ProductViewModel

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule() // For LiveData testing

    @Before
    fun setUp() {
        viewModel = ProductViewModel(repository)
    }

    @Test
    fun `getAllProducts should set liveProduct with Success when API response is successful`() = runTest {
        // Mock API response
        val mockProducts = Products()
        val response = Response.success(mockProducts)
        Mockito.`when`(repository.getAllProduct()).thenReturn(response)

        // Observe LiveData
        val observer = mock<Observer<com.example.common.NetworkCall<Products>>>()
        viewModel.liveProduct.observeForever(observer)

        // Call the function
        viewModel.getallProducts()

        // Verify
        Mockito.verify(observer).onChanged(com.example.common.NetworkCall.Loading())
        Mockito.verify(observer).onChanged(com.example.common.NetworkCall.Success(mockProducts))
    }

    @Test
    fun `getAllProducts should set liveProduct with Error when API response is unsuccessful`() = runTest {
        // Mock API error response
        val response = Response.error<Products>(400, ResponseBody.create(null, ""))
        Mockito.`when`(repository.getAllProduct()).thenReturn(response)

        // Observe LiveData
        val observer = mock<Observer<NetworkCall<Products>>>()
        viewModel.liveProduct.observeForever(observer)

        // Call the function
        viewModel.getallProducts()

        // Verify
        Mockito.verify(observer).onChanged(com.example.common.NetworkCall.Loading())
        Mockito.verify(observer).onChanged(com.example.common.NetworkCall.Error("An Error Occured"))
    }

    @Test
    fun `insertProductId calls repository to insert product`() = runTest {
        val mockProductEntity = com.example.data.room.ProductEntity(1, ItemCategory("Test"))
        viewModel.insertProductId(mockProductEntity)

        Mockito.verify(repository).InsertCategory(mockProductEntity)
    }
}
