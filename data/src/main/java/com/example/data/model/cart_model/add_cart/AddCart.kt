package com.example.data.model.cart_model.add_cart

data class AddCart(
    val date: String,
    val products: List<Product>,
    val userId: Int
)