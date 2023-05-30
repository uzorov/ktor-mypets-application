package com.example.data.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val name: String = "Узоров Кирилл",
    val mail: String = "uzorov@mail.ru",
    val phone: String = "89109867534",
)



