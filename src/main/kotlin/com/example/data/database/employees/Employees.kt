package com.example.data.database.employees

import com.example.data.database.users.Clients
import com.example.features.employee.EmployeeResponse
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

object Employees : Table("employee") {
    val id = Employees.integer("employeeid")
    val name = Employees.text("name")
    val position = Employees.text("position")
    val phone_number = Employees.text("phone_number")
    val mail = Employees.text("mail")
    val password = Employees.text("password")

    fun get(phoneNumber: String): EmployeeResponse {
        return transaction {
            val employeeModel = Employees.select {
                Employees.phone_number.eq(phoneNumber)
            }.single()

            EmployeeResponse(
                id = employeeModel[Employees.id],
                name = employeeModel[Employees.name],
                position = employeeModel[Employees.position],
                phone_number = employeeModel[Employees.phone_number],
                mail = employeeModel[Employees.mail],
                password = employeeModel[Employees.password]
                )
        }

    }

    fun fetchAllEmployeePhoneNumbers(): List<String> {
        return transaction {
            Employees.slice(Employees.phone_number).selectAll().map { row ->
                row[Employees.phone_number]
            }
        }
    }
}