package com.example.features.employee

import com.example.mypetsapplications.database.model.Application
import kotlinx.serialization.Serializable

@Serializable
data class EmployeeReceive(
    val phoneNumber: String,
)

@Serializable
data class EmployeeResponse(
    val id: Int?,
    val name: String?,
    val position: String?,
    val phone_number: String?,
    val mail: String?,
    val password: String?
    )

