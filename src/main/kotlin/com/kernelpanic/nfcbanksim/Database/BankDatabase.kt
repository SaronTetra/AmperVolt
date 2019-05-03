package com.kernelpanic.nfcbanksim.Database

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.joda.time.DateTime
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class BankDatabase {
    init {
        Database.connect(Secret.databaseURL, driver = "com.mysql.jdbc.Driver",
                user = Secret.databaseLogin, password = Secret.databasePassword)

    }

    fun printAllClients(){

        transaction {
            Client.selectAll().forEach{
                println("Client: "+ it[Client.name] + "\tBalance: " + it[Client.balance])
            }
        }
    }

    fun signUp(name_: String, login_: String, password_: String){

        transaction {
            val id = Client.insertAndGetId {
                it[name] = name_
                it[login] = login_
                it[password] = password_
//                it[balance] = 0.0


            }
        }
    }



}