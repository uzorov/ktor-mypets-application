package com.example.features.employee


import com.example.data.database.employees.Employees
import com.example.data.database.tokens.TokenDTO
import com.example.data.database.tokens.Tokens
import com.example.data.database.users.Clients
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*



import org.jetbrains.exposed.sql.insert
import java.util.*
import java.util.logging.Logger

class EmployeeController (private val call: ApplicationCall) {

    suspend fun fetchEmployee() {
        val employeePhoneReceive = call.receive<EmployeeReceive>()
        val employeeDTO = Employees.get(employeePhoneReceive.phoneNumber)

        call.respond(employeeDTO)
    }

    suspend fun fetchEmployeesPhoneNumbers() {
        call.respond(Employees.fetchAllEmployeePhoneNumbers())
    }
}