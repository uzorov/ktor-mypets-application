package com.example.data.database.tokens

import com.example.data.database.users.Clients
import com.example.data.database.users.UserDTO
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

object Tokens : Table("token") {
    private val phone_number = Tokens.text("phone_number")
    private val tokens = Tokens.text("token")

    fun insert(tokenDTO: TokenDTO) {
        transaction {
            Tokens.insert { table ->
                table[phone_number] = tokenDTO.phone_number
                table[tokens] = tokenDTO.token

            }
        }
    }

}