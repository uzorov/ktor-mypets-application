package com.example.data.database.users

class UserDTO(
    val id: Int,
    val name: String,
    val mail: String,
    val phone_number: String,
    val password: String,
    )

class UserWithoutIdDTO(
    val name: String,
    val mail: String,
    val phone_number: String,
    val password: String,
)