package com.example.features.animals

import com.example.data.database.animals.Animals
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

class AnimalController(private val call: ApplicationCall) {

    suspend fun getPets() {
        call.respond(Animals.fetchAllAnimals())
    }

    suspend fun getOnePet() {
        val animalId = call.receive<AnimalsIdReceive>()
        call.respond(Animals.fetchAnimal(animalId.id)!!)
    }
}