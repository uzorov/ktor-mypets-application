package com.example.features.users

import com.example.data.database.users.Clients
import com.example.data.model.Animal
import com.example.temp_data.InMemoryCash
import com.example.temp_data.TokenCash
import com.example.utils.isPhoneNumberValid
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.*

fun Application.configureUsersFetchingRouting() {
    routing {
        post("/fetch_user") {

            val userController = UserController(call)
            userController.getUser()
        }
    }
}