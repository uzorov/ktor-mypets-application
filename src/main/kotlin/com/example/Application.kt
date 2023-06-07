package com.example

import com.example.features.animals.configureAnimalRouting
import com.example.features.applications.configureApplicationRouting
import com.example.features.employee.configureEmployeeRouting
import com.example.features.login.configureLoginRouting
import com.example.features.registration.configureRegistrationRouting
import com.example.features.users.configureUsersFetchingRouting
import com.example.plugins.configureRouting
import com.example.plugins.configureSerialization
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.jetbrains.exposed.sql.Database

//const val PORT = "7366"
//const val DATABASE_NAME = "railway"
//postgresql://postgres:pBeAB2Iq3MTjyFKwPbAp@containers-us-west-66.railway.app:7366/railway
const val DATABASE_URL = "jdbc:postgresql://localhost:5432/mypets"
const val USER_NAME = "postgres"
const val PASSWORD = "uzorov"
fun main() {


  Database.connect(DATABASE_URL, driver = "org.postgresql.Driver",
       user = USER_NAME, password = PASSWORD)
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}




fun Application.module() {
    configureRouting()
    configureSerialization()

    configureLoginRouting()
    configureRegistrationRouting()
    configureUsersFetchingRouting()
    configureEmployeeRouting()
    configureAnimalRouting()
    configureApplicationRouting()
}
