package com.example.data.model.update_user

data class UpdateUser(
    val address: Address,
    val email: String,
    val name: Name,
    val password: String,
    val phone: String,
    val username: String
)