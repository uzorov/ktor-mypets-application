package com.example.features.registration

import kotlinx.serialization.Serializable

@Serializable
data class RegistrationReceive (
    val name: String,
    val phoneNumber: String,
    val password: String,
    val email: String,
    )

@Serializable
data class RegistrationResponse (
    val token: String,
    val isAdmin: Boolean,
    val isPasswordCorrect: Boolean,
    val isPhoneNumberValid: Boolean,
    val isPasswordValid: Boolean
)