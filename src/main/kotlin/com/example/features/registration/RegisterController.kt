package com.example.features.registration

import com.example.data.database.tokens.TokenDTO
import com.example.data.database.tokens.Tokens
import com.example.data.database.users.Clients
import com.example.data.database.users.UserDTO
import com.example.temp_data.InMemoryCash
import com.example.temp_data.TokenCash
import com.example.utils.isPhoneNumberValid
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import org.jetbrains.exposed.exceptions.ExposedSQLException
import java.util.*

class RegisterController(private val call: ApplicationCall) {


    suspend fun registerNewUser() {

        val registrationReceive = call.receive<RegistrationReceive>()

        if (!registrationReceive.phoneNumber.isPhoneNumberValid())
        {
            call.respond(HttpStatusCode.BadRequest, "Phone number isn`t valid")
        }

        val userDTO = Clients.fetchUser(registrationReceive.phoneNumber)

        if (userDTO != null) {
            call.respond(HttpStatusCode.Conflict, "User already exists")
        } else {

            try {
                Clients.insert(
                    UserDTO(
                        name = registrationReceive.name,
                        mail = registrationReceive.email,
                        phone_number = registrationReceive.phoneNumber,
                        password = registrationReceive.password
                    )
                )

            } catch (e: ExposedSQLException) {
                call.respond(HttpStatusCode.Conflict, "User already exists")
            }

            val token = UUID.randomUUID().toString()

            Tokens.insert(
                tokenDTO = TokenDTO(
                    phone_number = registrationReceive.phoneNumber,
                    token = token
                )
            )

            call.respond(RegistrationResponse(token = token))
        }



    }
}