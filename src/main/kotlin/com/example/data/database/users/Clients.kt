package com.example.data.database.users

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import java.lang.Exception

object Clients: Table("client") {
    val id = Clients.integer("clientid")
    val name = Clients.text("name")
    val mail = Clients.text("mail")
    val phone_number = Clients.text("phone_number")
    val password = Clients.text("password")

    fun insert(userDTO: UserWithoutIdDTO) {
        transaction {
            Clients.insert { table ->
                table[name] = userDTO.name
                table[mail] = userDTO.mail
                table[phone_number] = userDTO.phone_number
                table[password] = userDTO.password
            }
        }
    }

    fun fetchUser(phoneNumber: String) : UserDTO? {
        var userDTO: UserDTO? = null

            transaction {

                try {
                    val userModel = Clients.select {
                        Clients.phone_number.eq(phoneNumber)
                    }.single()

                    userDTO = UserDTO(
                        id = userModel[Clients.id],
                        name = userModel[Clients.name],
                        mail = userModel[Clients.mail],
                        phone_number = userModel[Clients.phone_number],
                        password = userModel[Clients.password]
                    )
                }
                catch (e: NoSuchElementException) {
                    e.printStackTrace()
                }
            }

        return userDTO
    }
}