package com.example.features.users

import com.example.data.database.users.Clients
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

class UserController(private val call: ApplicationCall)
{
    suspend fun getUser() {
        val usersReceivedPhoneNumber = call.receive<UserReceive>()

        val userDTO = Clients.fetchUser(usersReceivedPhoneNumber.phoneNumber)

        if (userDTO != null) {

            call.respond(UserResponse(
                id = userDTO.id,
                name = userDTO.name,
                mail = userDTO.mail,
                phone = userDTO.phone_number
            ))
        }
        else
        {
            call.respond(HttpStatusCode.BadRequest, "User with provided number don`t exists")
        }

    }


}