package com.example.plugins

import com.example.data.model.Animal
import com.example.features.registration.RegistrationReceive
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*

fun Application.configureRouting() {
    routing {
        get("/") {
            //call.respond(RegistrationReceive("Kirill", "123", "123"))
            call.respond("Hello world!")
        }
    }
}
