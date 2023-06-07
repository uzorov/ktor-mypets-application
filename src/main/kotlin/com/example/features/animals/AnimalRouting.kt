package com.example.features.animals

import com.example.features.employee.EmployeeController
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureAnimalRouting() {
    routing {
        get("/fetch_all_animals") {
            val animalController = AnimalController(call)
            animalController.getPets()
        }

        post("/fetch_one_animal") {
            val animalController = AnimalController(call)
            animalController.getOnePet()
        }
    }
}