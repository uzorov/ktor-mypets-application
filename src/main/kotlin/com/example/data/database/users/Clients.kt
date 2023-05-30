package com.example.data.database.users

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import java.lang.Exception

object Clients: Table("client") {
    private val name = Clients.text("name")
    private val mail = Clients.text("mail")
    private val phone_number = Clients.text("phone_number")
    private val password = Clients.text("password")

    fun insert(userDTO: UserDTO) {
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
       return try {
            transaction {
                val userModel = Clients.select {
                    Clients.phone_number.eq(phoneNumber)
                }.single()

                UserDTO(
                    name = userModel[Clients.name],
                    mail = userModel[Clients.mail],
                    phone_number = userModel[Clients.phone_number],
                    password = userModel[Clients.password]
                )
            }
        }
        catch (e: Exception)
        {
            e.printStackTrace()
            null
        }
    }
}