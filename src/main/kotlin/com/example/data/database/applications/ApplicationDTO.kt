package com.example.data.database.applications

import com.example.data.model.Animal
import com.example.data.model.Employee
import com.example.features.users.UserResponse
import com.example.mypetsapplications.database.model.ApplicationStatus

data class ApplicationDTO(
    val client: Int,
    val animal: Int,
    val hosingConditions: String = "",
    val aboutTheClient: String = "",
    val usersCity: String = "",
    val employee: Employee? = null,
    val applicationStatus: String = ApplicationStatus.PROCEED,
    val employeeComment: String = ""
)
