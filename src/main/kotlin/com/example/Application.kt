package com.example

import com.example.features.login.configureLoginRouting
import com.example.features.registration.configureRegistrationRouting
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.example.plugins.*
import org.jetbrains.exposed.sql.Database

const val PORT = "5432"
const val DATABASE_NAME = "mypetsferf"
const val USER_NAME = "postgresferfer"
const val PASSWORD = "uzoroverfe"
fun main() {

    /*try {
    Database.connect("jdbc:postgresql://localhost:$PORT/$DATABASE_NAME", driver = "org.postgresql.Driver",
        user = USER_NAME, password = PASSWORD)}
    catch (e: Exception)
    {
        println("No connection")
    }*/

    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureRouting()
    //configureSerialization()
    //configureDatabases()
    //configureLoginRouting()
    //configureRegistrationRouting()
}
