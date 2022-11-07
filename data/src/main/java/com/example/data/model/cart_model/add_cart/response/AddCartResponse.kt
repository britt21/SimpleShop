package com.example.data.model.cart_model.add_cart.response

data class AddCartResponse(
    val date: String,
    val id: Int,
    val products: List<Product>,
    val userId: Int
)