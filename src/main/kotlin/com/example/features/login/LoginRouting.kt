package com.example.features.login

import com.example.data.model.Animal
import com.example.temp_data.InMemoryCash
import com.example.temp_data.TokenCash
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.*

fun Application.configureLoginRouting() {
    routing {
        post("/login") {


            val loginController = LoginController(call)
            loginController.performLogin()
        }
    }
}