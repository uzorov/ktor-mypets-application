package com.example.mypetsapplications.database.model

import com.example.data.model.Animal
import com.example.data.model.Employee
import com.example.data.model.User
import kotlinx.serialization.Serializable

object ApplicationStatus {
    const val NOT_SEND = "Отправлена"
    const val SEND = "В обработке"
    const val PROCEED = "Принята"
    const val DECLINED = "Отклонена"
}

@Serializable
data class Application(
    val idNumber: Int = -1,
    val client: User = User(),
    val animal: Animal = Animal(),
    val hosingConditions: String = "",
    val aboutTheClient: String = "",
    val usersCity: String = "",
    val employee: Employee? = null,
    val applicationStatus: String = ApplicationStatus.PROCEED,
    val employeeComment: String = ""
)
