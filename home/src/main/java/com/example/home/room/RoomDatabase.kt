package com.example.home.room


import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "product_table")
data class ProductEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val itemCategory: ItemCategory
)


data class ItemCategory(
    val categoryId: String
)

