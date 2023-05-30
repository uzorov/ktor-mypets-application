package com.example.data.model

import com.example.mypetsapplications.database.model.Application
import kotlinx.serialization.Serializable

@Serializable
data class Employee(
    val name: String = "",
    val position: String = "",
    val phone_number: String = "",
    val mail: String = "",
    val applications: MutableList<Application> = mutableListOf<Application>()
)