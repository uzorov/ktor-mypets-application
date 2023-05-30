package com.example.temp_data

import com.example.features.registration.RegistrationReceive

data class TokenCash(
    val login : String,
    val token : String
)

object InMemoryCash {
    val registrationDataList = mutableListOf<RegistrationReceive>()
    val tokenList = mutableListOf<TokenCash>()
}