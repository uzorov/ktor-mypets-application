package com.example.features.applications

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureApplicationRouting() {
    routing {

        post("/fetch_apps_by_user_id") {
            val applicationController = ApplicationController(call)
            applicationController.getApplicationsForUserById()
        }

        post("/fetch_apps_by_employee_id") {
            val applicationController = ApplicationController(call)
            applicationController.getApplicationsForEmployeeById()
        }

        post ( "/add_application" ) {
            val applicationController = ApplicationController(call)
            applicationController.addNewApplication()
        }

        post ("/delete_application") {
            val applicationController = ApplicationController(call)
            applicationController.deleteApplication()
        }

        post ("/update_application") {
            val applicationController = ApplicationController(call)
            applicationController.updateApplication()
        }
    }
}