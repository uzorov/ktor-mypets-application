package com.example.features.users

import kotlinx.serialization.Serializable

@Serializable
data class UserReceive (
    val phoneNumber: String
)

@Serializable
data class UserResponse(
    val id: Int = 1,
    val name: String = "",
    val mail: String = "",
    val phone: String = ""
)