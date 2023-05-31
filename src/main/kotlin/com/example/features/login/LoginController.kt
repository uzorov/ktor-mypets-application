package com.example.features.login


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

class LoginController (private val call: ApplicationCall) {

    suspend fun performLogin() {


        val loginReceive = call.receive<LoginReceiveRemote>()
        val userDTO = Clients.fetchUser(loginReceive.login)
        println("receive -> $loginReceive userDTO -> $userDTO")
        if (userDTO == null) {
            call.respond(HttpStatusCode.BadRequest, "User not exists")
        }
        else {
            if (userDTO.password == loginReceive.password) {
                val token = UUID.randomUUID().toString()
                println("receive -> $loginReceive userDTO -> $userDTO")
                Tokens.insert(  TokenDTO(
                    phone_number = loginReceive.login,
                    token = token
                ))

                call.respond(LoginResponseRemote(token = token))
            }
            else {
                call.respond(HttpStatusCode.BadRequest, "Wrong Password")
            }
        }

        call.respond(HttpStatusCode.BadRequest)
    }
}