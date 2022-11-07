package com.example.home.room

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.data.model.product_model.Products
import com.example.data.model.product_model.single_product.SingleProduct
import com.example.data.room.*
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class DatabaseTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: ProductDatabase
    private lateinit var dao: ProductDao



    @Before
    fun setUp(){
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ProductDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.productDao
    }
    @After
    fun tearDown(){
        database.close()
    }

    @Test
    fun InsertProductPasssProductIsPopulated() {
        runTest {
            var category = ItemCategory("bag")
            val productEntity = ProductEntity(1,category)
            dao.InsertCategory(productEntity)


            dao.ReadCart().getOrAwaitValueUnitTest()
            val value = dao.ReadCategory().getOrAwaitValueUnitTest()
            assertThat(value).isNotEqualTo(null)

        }
    }

    @Test
    fun insertSingleProduct(){
        runTest {

            val singleProducts = CartEntity(1,CartData("man","new",0.0))
            dao.InsertCart(singleProducts)
            val data = dao.ReadCart().getOrAwaitValueUnitTest()
            assertThat(data).isNotEqualTo(null)
        }
    }
}