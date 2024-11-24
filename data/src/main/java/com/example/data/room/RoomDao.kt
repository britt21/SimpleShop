package com.example.data.room

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import com.example.data.model.product_model.Products
import com.example.data.model.product_model.ProductsItem

@Entity(tableName = "AllproductsEntity")
data class AllProductEntity(
    @PrimaryKey val id: Int,
    var productsItem: Products
)
