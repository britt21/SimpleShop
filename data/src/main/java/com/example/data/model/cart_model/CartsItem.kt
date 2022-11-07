package com.example.data.model.cart_model

data class CartsItem(
    val __v: Int,
    val date: String,
    val id: Int,
    val products: List<Product>,
    val userId: Int
)