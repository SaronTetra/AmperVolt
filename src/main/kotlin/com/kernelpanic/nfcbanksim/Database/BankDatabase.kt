package com.kernelpanic.nfcbanksim.Database

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class BankDatabase {
    init {
        println("Init")
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



}