package com.example.features.applications

import com.example.features.animals.AnimalResponse
import com.example.features.employee.EmployeeResponse
import com.example.features.users.UserResponse
import com.example.mypetsapplications.database.model.ApplicationStatus
import kotlinx.serialization.Serializable

object ApplicationStatus {
    const val NOT_SEND = "Отправлена"
    const val SEND = "В обработке"
    const val PROCEED = "Принята"
    const val DECLINED = "Отклонена"
}

@Serializable
data class ApplicationResponse(
    val applicationId: Int,
    val client: UserResponse,
    val animal: AnimalResponse,
    val hosingConditions: String = "",
    val aboutTheClient: String = "",
    val usersCity: String = "",
    val employee: EmployeeResponse? = null,
    val applicationStatus: String = ApplicationStatus.PROCEED,
    val employeeComment: String? = null
)

@Serializable
data class ApplicationReceive(
    val client: Int,
    val animal: Int,
    val hosingConditions: String = "",
    val aboutTheClient: String = "",
    val usersCity: String = "",
    var employee: Int? = null,
    var applicationStatus: String = ApplicationStatus.PROCEED,
    val employeeComment: String? = null
)

@Serializable
data class FullApplicationWithIdReceive(
    val id: Int,
    val client: Int,
    val animal: Int,
    val hosingConditions: String = "",
    val aboutTheClient: String = "",
    val usersCity: String = "",
    val employee: Int? = null,
    val applicationStatus: String = ApplicationStatus.PROCEED,
    val employeeComment: String? = null
)

@Serializable
data class ApplicationIdReceive(
    val id: Int,
)


@Serializable
data class ApplicationGetResponse(
    val code: Int,
)