package com.kernelpanic.nfcbanksim.Database

import com.kernelpanic.nfcbanksim.GET.GetClient
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.like
import org.jetbrains.exposed.sql.transactions.transaction
import org.joda.time.DateTime

class BankDatabase {
    init {
        Database.connect(Secret.databaseURL, driver = "com.mysql.jdbc.Driver",
                user = Secret.databaseLogin, password = Secret.databasePassword)

    }

    fun printAllClients() {

        transaction {
            Client
                    .selectAll()
                    .forEach {
                println("Client: " + it[Client.name] + "\tBalance: " + it[Client.balance])
            }
        }
    }

    fun signUp(name: String, login: String, password: String) {

        //DSL
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
            Client
                    .select(Client.login like login)
                    .forEach {
                        result.id = it[Client.id].value
                        result.name = it[Client.name]
                        result.login = it[Client.login]
                        result.creationDate = it[Client.creationDate].toString()
                        result.balance = it[Client.balance]

                    }
//            println("Name: ${result.name}\tBalance ${result.balance}")
        }
        return result
    }


    fun deleteAccount(login: String) {
        transaction {
            Client
                    .select { Client.login like login }
                    .forEach { itr ->
                ExClient.insertAndGetId {
                    it[this.name] = itr[Client.name]
                    it[this.login] = itr[Client.login]
                    it[this.created] = itr[Client.creationDate]
                    it[this.previousID] = itr[Client.id].value
                }

            }
            Client.deleteWhere { Client.login like login }
        }
    }

    fun putMoney(login: String, moneyPut: Double, title: String) {
        transaction {

            Client
                    .select(Client.login like login)
                    .forEach { itr ->
                        //println("\n\nId of client:" + itr[Client.id].value + "\n\n")
                        val id = itr[Client.id].value

                        Bank_Transaction.insertAndGetId {
                            it[this.fromId] = id
                            it[this.toId] = id
                            it[this.money] = moneyPut
                            it[this.type] = "PUT"
                            it[this.title] = title
                        }


                        Client.update({ Client.login like login }) {
                            with(SqlExpressionBuilder) {
                                it.update(Client.balance, Client.balance + moneyPut)
                            }
                        }
                    }
        }
    }



    fun doTransaction(login: String, destinationLogin: String, moneyPut: Double, title: String){

        transaction {
            Client
                    .select(Client.login like login)
                    .forEach { itr ->
                        val id = itr[Client.id].value
                        Client
                                .select(Client.login like destinationLogin)
                                .forEach{ itrnd ->
                                    val destId = itrnd[Client.id].value

                                    Bank_Transaction.insertAndGetId {
                                        it[this.fromId] = id
                                        it[this.toId] = destId
                                        it[this.money] = moneyPut
                                        it[this.type] = "TRN"
                                        it[this.title] = title
                                    }
                                }

                        Client.update({ Client.login like login }) {
                            with(SqlExpressionBuilder) {
                                it.update(Client.balance, Client.balance - moneyPut)
                            }
                        }

                        Client.update({ Client.login like destinationLogin }) {
                            with(SqlExpressionBuilder) {
                                it.update(Client.balance, Client.balance + moneyPut)
                            }
                        }
                    }
        }
    }


    fun addCard(cardNumber: String, cvc: Int, ownerLogin: String, pin: Int) {
//        var id = 0
//       Client.select {Client.login like ownerLogin}.forEach {
//           id = it[Client.id].value
//       }

        //DSL
        transaction {
            Card.insertAndGetId {
                it[this.number] = cardNumber
                it[this.cvc] = cvc
                it[this.ownerID] = getByLogin(ownerLogin).id
                it[this.pin] = pin
                it[this.date] = DateTime.now() + 31556926 * 2
            }

        }



    }
}

