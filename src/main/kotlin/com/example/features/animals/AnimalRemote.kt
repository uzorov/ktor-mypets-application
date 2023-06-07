package com.example.features.animals

import kotlinx.serialization.Serializable

@Serializable
data class AnimalResponse(
    val id: Int,
    val name: String,
    val age: Int,
    val type: String,
    val life_story: String,
    val picture: String
)

@Serializable
data class AnimalsIdReceive(
    val id: Int,
)