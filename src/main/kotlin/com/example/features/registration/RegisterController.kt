package com.example.features.registration

import com.example.data.database.employees.Employees
import com.example.data.database.tokens.TokenDTO
import com.example.data.database.tokens.Tokens
import com.example.data.database.users.Clients
import com.example.data.database.users.UserDTO
import com.example.data.database.users.UserWithoutIdDTO
import com.example.utils.isAdmin
import com.example.utils.isPasswordValid
import com.example.utils.isPhoneNumberValid
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import org.jetbrains.exposed.exceptions.ExposedSQLException
import java.util.*

object HTTP_CODES {
    const val WRONG_PASSWORD = 2
    const val THIS_IS_EMPLOYEE = 3
    const val SUCCESS = 4
}


class RegisterController(private val call: ApplicationCall) {


    suspend fun registerNewUser() {

        val registrationReceive = call.receive<RegistrationReceive>()

        val isAdmin = registrationReceive.phoneNumber.isAdmin()

        var isPasswordCorrect = true
        val isPhoneNumberValid = registrationReceive.phoneNumber.isPhoneNumberValid()
        val isPasswordValid = registrationReceive.password.isPasswordValid()


        //If user is Admin them just response with isAdmin = true and token
        //If user not an Admin then check if he exists or not
        //If user exists check his password
        //If password incorrect then response with BadRequest
        //Else response with token
        // If user not exists then create a new user and then response
        if (!isAdmin) {
            if (isUserExists(registrationReceive.phoneNumber)) {
                if (!isPasswordCorrect(registrationReceive.phoneNumber, registrationReceive.password)) {
                    isPasswordCorrect = false }
            } else if (isPasswordValid && isPhoneNumberValid) {
                try {
                    Clients.insert(
                        UserWithoutIdDTO(
                            name = registrationReceive.name,
                            mail = registrationReceive.email,
                            phone_number = registrationReceive.phoneNumber,
                            password = registrationReceive.password,
                        )
                    )
                } catch (e: ExposedSQLException) {
                    call.respond(HttpStatusCode.Conflict, "Something went wrong due data insertion")
                }
            }
        } else {
            isPasswordCorrect = isEmployeePasswordCorrect(registrationReceive.phoneNumber, registrationReceive.password)
        }



        call.respond(
            RegistrationResponse(
                token = if (isPasswordCorrect && isPhoneNumberValid && isPasswordValid) GenerateAndInsertToken(
                    registrationReceive.phoneNumber
                ) else "",
                isAdmin = isAdmin,
                isPasswordCorrect = isPasswordCorrect,
                isPhoneNumberValid = isPhoneNumberValid,
                isPasswordValid = isPasswordValid
            )
        )

    }

    private fun isPasswordCorrect(phoneNumber: String, password: String): Boolean {
        val userDTO = Clients.fetchUser(phoneNumber)
        return userDTO!!.password == password
    }


    private fun isEmployeePasswordCorrect(phoneNumber: String, password: String): Boolean {
        val employeeDTO = Employees.get(phoneNumber)
        return employeeDTO.password == password
    }


    private fun GenerateAndInsertToken(phoneNumber: String): String {
        val token = UUID.randomUUID().toString()
        Tokens.insert(
            TokenDTO(
                phone_number = phoneNumber,
                token = token
            )
        )

        return token
    }

    private fun isUserExists(phoneNumber: String): Boolean {
        val userDTO = Clients.fetchUser(phoneNumber)
        return userDTO != null
    }
}