package com.example.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @androidx.room.Insert(onConflict = OnConflictStrategy.REPLACE)
    fun InsertCategory(productEntity: ProductEntity)


    @Query("SELECT * FROM ProductEntity")
    fun ReadCategory(): LiveData<ProductEntity>


    @androidx.room.Insert(onConflict = OnConflictStrategy.REPLACE)
    fun InsertCart(cartentity: CartEntity)


    @Query("SELECT * FROM CartEntity")
    fun ReadCart(): Flow<List<CartEntity>>
}
