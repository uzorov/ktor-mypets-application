package com.example.features.employee

import com.example.data.model.Animal
import com.example.temp_data.InMemoryCash
import com.example.temp_data.TokenCash
import io.ktor.client.*
import io.ktor.client.plugins.logging.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.*

fun Application.configureEmployeeRouting() {
    routing {
        post("/fetch_employee") {
            val employeeController = EmployeeController(call)
            employeeController.fetchEmployee()
        }

        get("/fetch_employees_numbers") {
            val employeeController = EmployeeController(call)
            employeeController.fetchEmployeesPhoneNumbers()
        }
    }
}