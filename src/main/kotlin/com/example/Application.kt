package com.example

import com.example.features.login.configureLoginRouting
import com.example.features.registration.configureRegistrationRouting
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.example.plugins.*
import org.jetbrains.exposed.sql.Database

const val DATABASE_URL = "postgresql://postgres:pBeAB2Iq3MTjyFKwPbAp@containers-us-west-66.railway.app:7366/railway"
const val PORT = "7366"
const val DATABASE_NAME = "railway"
const val USER_NAME = "postgres"
const val PASSWORD = "pBeAB2Iq3MTjyFKwPbAp"
fun main() {


    Database.connect("jdbc:$DATABASE_URL:$PORT/$DATABASE_NAME", driver = "org.postgresql.Driver",
        user = USER_NAME, password = PASSWORD)

    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureRouting()
    configureSerialization()
    configureDatabases()
    configureLoginRouting()
    configureRegistrationRouting()
}
