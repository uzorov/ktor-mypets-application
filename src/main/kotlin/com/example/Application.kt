package com.example

import com.example.features.login.configureLoginRouting
import com.example.features.registration.configureRegistrationRouting
import com.example.plugins.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.jetbrains.exposed.sql.Database
import java.sql.Connection
import java.sql.DriverManager


const val DATABASE_URL = "jdbc:postgresql://localhost:7366/railway"
const val PORT = "7366"
const val DATABASE_NAME = "railway"
const val USER_NAME = "postgres"
const val PASSWORD = "pBeAB2Iq3MTjyFKwPbAp"
fun main() {


    Database.connect(DATABASE_URL, driver = "org.postgresql.Driver",
        user = USER_NAME, password = PASSWORD)

    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}


class DatabaseConnection {
    private val jdbcUrl = "jdbc:postgresql://localhost:5432/Railway_Reservation"
    private val username = "postgres"
    private val password = "aakash26"

    @get:Throws(Exception::class)
    val connection: Connection
        get() {
            Class.forName("org.postgresql.Driver")
            return DriverManager.getConnection(jdbcUrl, username, password)
        }
}

fun Application.module() {
    configureRouting()
    configureSerialization()
    //configureDatabases()
    configureLoginRouting()
    configureRegistrationRouting()
}
