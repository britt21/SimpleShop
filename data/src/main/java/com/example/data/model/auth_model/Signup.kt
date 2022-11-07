package com.example.data.model.auth_model

data class Signup(
    val address: Address,
    val email: String,
    val name: Name,
    val password: String,
    val phone: String,
    val username: String
)