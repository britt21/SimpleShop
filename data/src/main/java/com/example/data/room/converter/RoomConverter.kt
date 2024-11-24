package com.example.data.room.converter

import android.graphics.drawable.Drawable
import androidx.room.TypeConverter
import com.example.data.model.product_model.Products
import com.example.data.model.product_model.ProductsItem
import com.example.data.room.CartData
import com.example.data.room.ItemCategory
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


val gson = Gson()


class RoomConverter {

    @TypeConverter
    fun fromDb(item : ItemCategory) : String{
        return gson.toJson(item)
    }
    @TypeConverter
    fun toNDb(item : String) : ItemCategory{
        val token  = object : TypeToken<ItemCategory>(){}.type
        return gson.fromJson(item,token)
    }

    @TypeConverter
    fun fromCart(item : CartData) : String{
        return gson.toJson(item)
    }
    @TypeConverter
    fun toCart(item : String) : CartData{
        val token  = object : TypeToken<CartData>(){}.type
        return gson.fromJson(item,token)
    }





    @TypeConverter
    fun fromprod(item : Products) : String{
        return gson.toJson(item)
    }
    @TypeConverter
    fun toprod(item : String) : Products{
        val token  = object : TypeToken<Products>(){}.type
        return gson.fromJson(item,token)
    }





}