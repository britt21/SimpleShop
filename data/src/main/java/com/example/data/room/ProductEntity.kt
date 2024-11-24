package com.example.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity
data class ProductEntity(
    @PrimaryKey(autoGenerate = true)
    var id : Long,
    var category : ItemCategory
)



@Entity
data class CartEntity(
    @PrimaryKey(autoGenerate = true)
    var id : Int,
    var cartData: CartData

)

data class CartData(
    var cartImg : String,
    var carttitle : String,
    var cartprice : Double
)


data class ItemCategory(
    var item : String
)