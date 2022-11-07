package com.example.data.model.update_user.response

data class UpdateUserResponse(
    val address: Address,
    val email: String,
    val name: Name,
    val password: String,
    val phone: String,
    val username: String
)