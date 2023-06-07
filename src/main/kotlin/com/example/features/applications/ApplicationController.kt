package com.example.features.applications

import com.example.data.database.applications.Applications
import com.example.mypetsapplications.database.model.Application
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

class ApplicationController(private val call: ApplicationCall) {
    public val SUCCESS = 4
    suspend fun getApplicationsForUserById() {
        val userIdReceive = call.receive<ApplicationIdReceive>()

        call.respond(Applications.fetchApplicationsForUser(userIdReceive.id))
    }

    suspend fun getApplicationsForEmployeeById() {
        val employeeIdReceive = call.receive<ApplicationIdReceive>()

        call.respond(Applications.fetchApplicationsForEmployee(employeeIdReceive.id))
    }

    suspend fun addNewApplication() {
        val application = call.receive<ApplicationReceive>()

        try {
            Applications.addApplication(application)
            call.respond(ApplicationGetResponse(SUCCESS))
        } catch (e: Exception) {
            call.respond(e.printStackTrace())
        }

    }

    suspend fun updateApplication() {
        val application = call.receive<FullApplicationWithIdReceive>()
        Applications.updateApplication(application)
        call.respond(SUCCESS)
    }

    suspend fun deleteApplication() {
        val applicationID = call.receive<ApplicationIdReceive>()
        Applications.deleteApplication(applicationID.id)
        call.respond(SUCCESS)
    }

}