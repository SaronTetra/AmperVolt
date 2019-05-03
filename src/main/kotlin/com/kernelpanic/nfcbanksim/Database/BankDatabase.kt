package com.kernelpanic.nfcbanksim.Database

import com.kernelpanic.nfcbanksim.GET.GetClient
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

    fun signUp(name: String, login: String, password: String){

        transaction {
            val id = Client.insertAndGetId {
                it[this.name] = name
                it[this.login] = login
                it[this.password] = password
            }

        }
    }


    fun getByLogin(login: String): GetClient {

        val result = GetClient()
        //var result = GetClient()
        transaction {
            Client.select{Client.login like login}.forEach{
                result.name = it[Client.name]
                result.login = it[Client.login]
                result.creationDate = it[Client.created].toString()
                result.balance = it[Client.balance]

            }
//            println("Name: ${result.name}\tBalance ${result.balance}")
        }
        return result
    }


    fun deleteAccount(login: String){
        transaction {
            Client.select{Client.login like login}.forEach{ itr ->
                ExClient.insertAndGetId {
                    it[this.name] = itr[Client.name]
                    it[this.login] = itr[Client.login]
                    it[this.created] = itr[Client.created]
                    it[this.previousID] = itr[Client.id].value
                }

            }
            Client.deleteWhere { Client.login like login }
        }
    }



}