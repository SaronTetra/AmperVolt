package com.kernelpanic.nfcbanksim.Database

import com.kernelpanic.nfcbanksim.GET.GetCard
import com.kernelpanic.nfcbanksim.GET.GetClient
import com.kernelpanic.nfcbanksim.GET.GetTransactions
import org.hibernate.validator.internal.util.Contracts.assertTrue
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.like
import org.jetbrains.exposed.sql.transactions.transaction
import org.joda.time.DateTime
import java.util.*
import kotlin.collections.ArrayList

class BankDatabase {
    init {
        Database.connect(Secret.databaseURL, driver = "com.mysql.jdbc.Driver",
                user = Secret.databaseLogin, password = Secret.databasePassword)

    }



//    fun printAllClients() { TODO test join with name and surname
//
//        transaction {
//            Client
//                    .selectAll()
//                    .forEach {
//                println("Client: " + it[Client.login] + "\tBalance: " + it[Client.balance])
//            }
//        }
//    }

    fun signUp(name: String, surname: String, login: String, password: String) {

        //DSL
        transaction {
            val clientId = Client.insertAndGetId {
                it[this.login] = login
                it[this.password] = password
            }

            val detailsId = Client_Details.insertAndGetId {
                it[this.name] = name
                it[this.surname] = surname
//                it[this.avatar] = avatar
            }

        }



    }
    fun createAccount(login: String, number: String){

        //TODO find id from login

        val client = getByLogin(login)
        transaction {
            val accId = Account.insertAndGetId {
                it[this.number] = number
                it[this.owner_id] = client.id
            }
        }
    }




    fun getByLogin(login: String): GetClient {

        val result = GetClient()
        transaction {
            Client
                    .select(Client.login like login)
                    .forEach {
                        result.id = it[Client.id].value
                        result.login = it[Client.login]
                        result.creationDate = it[Client.creationDate].toString()
                    }
//            println("Name: ${result.name}\tBalance ${result.balance}")
        }
        return result
    }


//
//    fun getUserByID(id: Int): GetClient {
//
//        val result = GetClient()
//                transaction {
//            Client.select { Client.id eq id }
//                    .forEach {
//                        result.id = it[Client.id].value
//                        result.name = it[Client.name]
//                        result.login = it[Client.login]
//                        result.creationDate = it[Client.creationDate].toString()
//                        result.balance = it[Client.balance]
//
//                    }
////            println("Name: ${result.name}\tBalance ${result.balance}")
//        }
//        return result
//    }
//
//    fun deleteAccount(login: String) {
//        transaction {
//            Client
//                    .select { Client.login like login }
//                    .forEach { itr ->
//                ExClient.insertAndGetId {
//                    it[this.name] = itr[Client.name]
//                    it[this.login] = itr[Client.login]
//                    it[this.created] = itr[Client.creationDate]
//                    it[this.previousID] = itr[Client.id].value
//                }
//
//            }
//            Client.deleteWhere { Client.login like login }
//        }
//    }
//
//    fun putMoney(login: String, moneyPut: Double, title: String) {
//        transaction {
//
//            Client
//                    .select(Client.login like login)
//                    .forEach { itr ->
//                        //println("\n\nId of client:" + itr[Client.id].value + "\n\n")
//                        val id = itr[Client.id].value
//
//                        Bank_Transaction.insertAndGetId {
//                            it[this.fromId] = id
//                            it[this.toId] = id
//                            it[this.money] = moneyPut
//                            it[this.type] = "PUT"
//                            it[this.title] = title
//                        }
//
//
//                        Client.update({ Client.login like login }) {
//                            with(SqlExpressionBuilder) {
//                                it.update(Client.balance, Client.balance + moneyPut)
//                            }
//                        }
//                    }
//        }
//    }
//
//    fun doTransaction(login: String, destinationLogin: String, moneyPut: Double, title: String){
//
//        transaction {
//            Client
//                    .select(Client.login like login)
//                    .forEach { itr ->
//                        val id = itr[Client.id].value
//                        Client
//                                .select(Client.login like destinationLogin)
//                                .forEach{ itrnd ->
//                                    val destId = itrnd[Client.id].value
//
//                                    Bank_Transaction.insertAndGetId {
//                                        it[this.fromId] = id
//                                        it[this.toId] = destId
//                                        it[this.money] = moneyPut
//                                        it[this.type] = "TRN"
//                                        it[this.title] = title
//                                    }
//                                }
//
//                        Client.update({ Client.login like login }) {
//                            with(SqlExpressionBuilder) {
//                                it.update(Client.balance, Client.balance - moneyPut)
//                            }
//                        }
//
//                        Client.update({ Client.login like destinationLogin }) {
//                            with(SqlExpressionBuilder) {
//                                it.update(Client.balance, Client.balance + moneyPut)
//                            }
//                        }
//                    }
//        }
//    }
//
//    fun getTransactions(login: String): ArrayList<GetTransactions>{
////        var result = arrayListOf(GetTransactions())
//        var result = arrayListOf<GetTransactions>()
//        transaction {
//            Bank_Transaction.select{
//                (Bank_Transaction.fromId eq getByLogin(login).id) or
//                        (Bank_Transaction.toId eq getByLogin(login).id)
//            }.forEach{
//                val fromLogin = getUserByID(it[Bank_Transaction.fromId]).login
//                val toLogin = getUserByID(it[Bank_Transaction.toId]).login
//                val money: Double = it[Bank_Transaction.money]
//                result.add(GetTransactions(it[Bank_Transaction.id].value,
//                        fromLogin,
//                        toLogin,
//                        if(fromLogin == login && it[Bank_Transaction.type] != "PUT"){
//                            -money
//                        }
//                        else{
//                            money
//                        },
//                        it[Bank_Transaction.type],
//                        it[Bank_Transaction.title],
//                        it[Bank_Transaction.orderDate].toString(),
//                        it[Bank_Transaction.executionDate].toString(),
//                        if(fromLogin == login) {
//                            toLogin
//                        }
//                        else{
//                            fromLogin
//                        }
//                )
//                )
//            }
//        }
//        return result
//    }
//
//    fun addCard(cardNumber: String, cvc: Int, ownerLogin: String, pin: Int) {
//
//        //DSL
//        transaction {
//            Card.insertAndGetId {
//                it[this.number] = cardNumber
//                it[this.cvc] = cvc
//                it[this.ownerID] = getByLogin(ownerLogin).id
//                it[this.pin] = pin
//                it[this.date] = DateTime.now() + 31556926000 * 2 //TODO: Find a bettter way to add 2 years to a date
//                it[this.uuid] = UUID.randomUUID().toString()
//            }
//
//        }
//    }
//
//    fun getCardByUUID(uuid: String): GetCard{
//        val result = GetCard()
//        transaction {
//            Card
//                    .select(Card.uuid like uuid)
//                    .forEach {
//                        result.number = it[Card.number]
//                        result.date = it[Card.date].toString()
//                        result.cvc = it[Card.cvc]
//                        result.ownerID = it[Card.ownerID]
//                        result.pin = it[Card.pin]
//                        result.uuid = it[Card.uuid]
//                    }
//        }
//        return result
//    }
//
//    fun cardTransactino(uuid: String, destinationLogin: String, moneyAmount: Double, title: String){
//    val card = getCardByUUID(uuid)
//
//        doTransaction(getUserByID(card.ownerID).login, destinationLogin, moneyAmount, title)
//    }
}

