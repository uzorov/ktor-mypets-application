package com.example.data.database.applications

import com.example.data.database.animals.Animals
import com.example.data.database.employees.Employees
import com.example.data.database.users.Clients
import com.example.features.animals.AnimalResponse
import com.example.features.applications.ApplicationReceive
import com.example.features.applications.ApplicationResponse
import com.example.features.applications.ApplicationStatus
import com.example.features.applications.FullApplicationWithIdReceive
import com.example.features.employee.EmployeeResponse
import com.example.features.users.UserResponse
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.Random

object Applications : Table("application") {
    private val applicationId = Applications.integer("applicationid").autoIncrement()
    val client = reference("client", Clients.id)
    val animal = reference("animal", Animals.id)
    private val hosingConditions = Applications.text("housing_conditions")
    private val aboutTheClient = Applications.text("about_the_client")
    private val usersCity = Applications.text("clients_city")
    val employee = optReference("employee", Employees.id)
    private val applicationStatus = Applications.text("application_status")
    private val employeeComment = Applications.text("employee_comment").nullable()


    fun fetchApplicationsForEmployee(employeeId: Int): List<ApplicationResponse> {
        return transaction {
            (((Applications innerJoin Clients) innerJoin Animals) leftJoin Employees).slice(
                Applications.applicationId,
                Clients.id,
                Clients.name,
                Clients.mail,
                Clients.phone_number,
                Animals.id,
                Animals.name,
                Animals.age,
                Animals.type,
                Animals.life_story,
                Animals.picture,
                Applications.hosingConditions,
                Applications.aboutTheClient,
                Applications.usersCity,
                Employees.id,
                Employees.name,
                Employees.position,
                Employees.mail,
                Employees.phone_number,
                Applications.applicationStatus,
                Applications.employeeComment
            ).select {
                Employees.id.eq(employeeId)
            }.map { row ->
                ApplicationResponse(
                    applicationId = row[Applications.applicationId],
                    client = UserResponse(
                        id = row[Clients.id],
                        name = row[Clients.name],
                        mail = row[Clients.mail],
                        phone = row[Clients.phone_number]
                    ),
                    animal = AnimalResponse(
                        id = row[Animals.id],
                        name = row[Animals.name],
                        age = row[Animals.age],
                        type = row[Animals.type],
                        life_story = row[Animals.life_story],
                        picture = row[Animals.picture]
                    ),
                    hosingConditions = row[Applications.hosingConditions],
                    aboutTheClient = row[Applications.aboutTheClient],
                    usersCity = row[Applications.usersCity],
                    employee = if (row.hasValue(Employees.name)) EmployeeResponse(
                        id = row[Employees.id],
                        name = row[Employees.name] ?: "",
                        position = row[Employees.position] ?: "",
                        phone_number = row[Employees.phone_number] ?: "",
                        mail = row[Employees.mail] ?: "",
                        password = ""
                    ) else null,
                    applicationStatus = row[Applications.applicationStatus],
                    employeeComment = row[Applications.employeeComment]
                )
            }
        }
    }


    fun addApplication(application: ApplicationReceive) {


        application.applicationStatus = ApplicationStatus.SEND
        application.employee = kotlin.random.Random.nextInt(1, Employees.fetchAllEmployeePhoneNumbers().size)
        transaction {
            Applications.insert {
                it[client] = application.client
                it[animal] = application.animal
                it[hosingConditions] = application.hosingConditions
                it[aboutTheClient] = application.aboutTheClient
                it[usersCity] = application.usersCity
                it[employee] = application.employee
                it[applicationStatus] = application.applicationStatus
                it[employeeComment] = application.employeeComment
            }
        }
    }

    fun updateApplication(application: FullApplicationWithIdReceive) {
        transaction {
            Applications.update({ Applications.applicationId eq application.id }) {
                it[client] = application.client
                it[animal] = application.animal
                it[hosingConditions] = application.hosingConditions
                it[aboutTheClient] = application.aboutTheClient
                it[usersCity] = application.usersCity
                it[employee] = application.employee
                it[applicationStatus] = application.applicationStatus
                it[employeeComment] = application.employeeComment
            }
        }
    }

    fun deleteApplication(applicationId: Int) {
        transaction {
            Applications.deleteWhere { Applications.applicationId eq applicationId }
        }
    }

    fun fetchApplicationsForUser(userId: Int): List<ApplicationResponse> {
        return transaction {
            (((Applications innerJoin Clients) innerJoin Animals) leftJoin Employees).slice(
                Applications.applicationId,
                Clients.id,
                Clients.name,
                Clients.mail,
                Clients.phone_number,
                Animals.id,
                Animals.name,
                Animals.age,
                Animals.type,
                Animals.life_story,
                Animals.picture,
                Applications.hosingConditions,
                Applications.aboutTheClient,
                Applications.usersCity,
                Employees.id,
                Employees.name,
                Employees.position,
                Employees.mail,
                Employees.phone_number,
                Applications.applicationStatus,
                Applications.employeeComment
            ).select {
                Clients.id.eq(userId)
            }.map { row ->
                ApplicationResponse(
                    applicationId = row[Applications.applicationId],
                    client = UserResponse(
                        id = row[Clients.id],
                        name = row[Clients.name],
                        mail = row[Clients.mail],
                        phone = row[Clients.phone_number]
                    ),
                    animal = AnimalResponse(
                        id = row[Animals.id],
                        name = row[Animals.name],
                        age = row[Animals.age],
                        type = row[Animals.type],
                        life_story = row[Animals.life_story],
                        picture = row[Animals.picture]
                    ),
                    hosingConditions = row[Applications.hosingConditions],
                    aboutTheClient = row[Applications.aboutTheClient],
                    usersCity = row[Applications.usersCity],
                    employee = if (row.getOrNull(Employees.id) != null) EmployeeResponse(
                        id = row.getOrNull(Employees.id),
                        name = row[Employees.name] ?: "",
                        position = row[Employees.position] ?: "",
                        phone_number = row[Employees.phone_number] ?: "",
                        mail = row[Employees.mail] ?: "",
                        password = ""
                    ) else null,
                    applicationStatus = row[Applications.applicationStatus],
                    employeeComment = row[Applications.employeeComment]
                )
            }
        }
    }
}