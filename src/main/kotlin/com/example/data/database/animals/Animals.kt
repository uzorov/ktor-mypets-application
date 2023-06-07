package com.example.data.database.animals

import com.example.features.animals.AnimalResponse
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

object Animals : Table("animal") {
    val id = Animals.integer("animalid")
    val name = Animals.text("name")
    val age = Animals.integer("age")
    val type = Animals.text("type")
    val life_story = Animals.text("life_story")
    val picture = Animals.text("picture")

    fun fetchAllAnimals(): List<AnimalResponse> {
        return transaction {
            Animals.selectAll().map { row ->
                AnimalResponse(
                    id = row[Animals.id],
                    name = row[Animals.name],
                    age = row[Animals.age],
                    type = row[Animals.type],
                    life_story = row[Animals.life_story],
                    picture = row[Animals.picture]
                )
            }
        }
    }

    fun fetchAnimal(animalId: Int): AnimalResponse? {
        return transaction {
            Animals.select { Animals.id eq animalId }.singleOrNull()?.let { row ->
                AnimalResponse(
                    id = row[Animals.id],
                    name = row[Animals.name],
                    age = row[Animals.age],
                    type = row[Animals.type],
                    life_story = row[Animals.life_story],
                    picture = row[Animals.picture]
                )
            }
        }
    }
}