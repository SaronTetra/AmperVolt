package com.kernelpanic.nfcbanksim.Database

import com.kernelpanic.nfcbanksim.GET.*
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


    fun signUp(name: String, surname: String, login: String, password: String) {

        //DSL
        transaction {
            val clientId = Client.insertAndGetId {
                it[this.login] = login
                it[this.password] = password
                it[this.name] = name
                it[this.surname] = surname
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
        }
        return result
    }

    fun getAccountByNumber(number: String): GetAccount {

        val result = GetAccount()
        transaction {
            Account
                    .select{Account.number like number}
                    .forEach {
                        result.id = it[Account.id].value
                        result.number = it[Account.number]
                        result.balance = it[Account.balance]
                        result.owner_id = it[Account.owner_id]
                    }
        }

        return result
    }



    fun getUserByID(id: Int): GetClient {

        val result = GetClient()
                transaction {
            Client.select { Client.id eq id }
                    .forEach {
                        result.id = it[Client.id].value
                        result.login = it[Client.login]
                        result.creationDate = it[Client.creationDate].toString()
                    }
        }
        return result
    }

    fun  getUserByLogin(login: String): GetClient {

        val result = GetClient()
                transaction {
            Client.select { Client.login like login }
                    .forEach {
                        result.id = it[Client.id].value
                        result.login = it[Client.login]
                        result.creationDate = it[Client.creationDate].toString()
                    }
        }
        return result
    }

    fun putMoney(accountNumber: String, moneyPut: Double) {
        transaction {

            Account
                    .select(Account.number like accountNumber)
                    .forEach{itr ->
                        val accNumb = itr[Account.number]


                        Bank_Transaction.insertAndGetId {
                            it[this.from_account] = accNumb
                            it[this.to_account] = accNumb
                            it[this.money] = moneyPut
                            it[this.type] = 1
                            it[this.title] = "Cash payment in bank"
                        }

                        Account.update({Account.number like accountNumber}){
                            with(SqlExpressionBuilder){
                                it.update(balance, balance + moneyPut)
                            }
                        }

                    }


        }
    }

    fun doTransaction(acc: String, destinationAcc: String, moneyPut: Double, title: String) {

        transaction {

            Account
                    .select { Account.number like acc }
                    .forEach { itr ->
                        val accNumb = itr[Account.number]

                        Bank_Transaction.insertAndGetId {
                            it[this.from_account] = accNumb
                            it[this.to_account] = destinationAcc
                            it[this.money] = moneyPut
                            it[this.type] = 2
                            it[this.title] = title
                        }


                        Account.update({ Account.number like acc }) {
                            with(SqlExpressionBuilder) {
                                it.update(balance, balance - moneyPut)

                            }

                            Account.update({ Account.number like destinationAcc }) {
                                with(SqlExpressionBuilder) {
                                    it.update(balance, balance + moneyPut)

                                }


                            }

                        }
                    }
        }
    }

    fun getTransactions(account: String): ArrayList<GetTransaction>{
        val result = arrayListOf<GetTransaction>()
        transaction {
            Bank_Transaction.select{
                (Bank_Transaction.from_account like getAccountByNumber(account).number) or
                        (Bank_Transaction.to_account like getAccountByNumber(account).number)
            }.forEach{


                val fromAcc = it[Bank_Transaction.from_account]
                val toAcc = it[Bank_Transaction.to_account]
                val money: Double = it[Bank_Transaction.money]
                result.add(GetTransaction(it[Bank_Transaction.id].value,
                        fromAcc,
                        toAcc,
                        if(fromAcc == account && it[Bank_Transaction.type] != 1){
                            -money
                        }
                        else{
                            money
                        },
                        it[Bank_Transaction.type],
                        it[Bank_Transaction.title],
                        it[Bank_Transaction.order_date].toString(),
                        if(fromAcc == account) {
                            toAcc
                        }
                        else{
                            fromAcc
                        },
                        getTransactionType(it[Bank_Transaction.type])
                )
                )


            }
        }
        return result
    }

    fun addCard(cardNumber: String, cvc: Int, ownerAccount: String, pin: Int) {

        //DSL
        transaction {
            Card.insertAndGetId {
                it[this.number] = cardNumber
                it[this.creationDate] = DateTime.now()
                it[this.expirationDate] = DateTime.now() + 31556926000 * 2 //TODO: Find a bettter way to add 2 years to a date
                it[this.cvc] = cvc
                it[this.ownerAccountID] = getAccountByNumber(ownerAccount).id
                it[this.pin] = pin
                it[this.uuid] = UUID.randomUUID().toString()
            }

        }
    }

    fun getCardByUUID(uuid: String): GetCard{
        val result = GetCard()
        transaction {
            Card
                    .select(Card.uuid like uuid)
                    .forEach {
                        result.number = it[Card.number]
                        result.creationDate= it[Card.creationDate].toString()
                        result.expirationDate = it[Card.expirationDate].toString()
                        result.cvc = it[Card.cvc]
                        result.ownerID = it[Card.ownerAccountID]
                        result.pin = it[Card.pin]
                        result.uuid = it[Card.uuid]
                    }
        }
        return result
    }

    fun getAccountByID(id: Int): GetAccount {
        val result = GetAccount()
        transaction {
            Account
                    .select{Account.id eq id}
                    .forEach {
                        result.number = it[Account.number]
                        result.balance = it[Account.balance]
                        result.owner_id = it[Account.owner_id]
                    }
        }
        return result
    }

    fun cardTransaction(uuid: String, destinationAccount: String, moneyAmount: Double, title: String){
        val card = getCardByUUID(uuid)
        val account = transaction { Account.select { Account.id eq card.ownerID } }

        doTransaction(getAccountByID(card.ownerID).number, destinationAccount, moneyAmount, title)
    }


    fun getUsers(): ArrayList<GetClient> {
        val result = arrayListOf<GetClient>()
        transaction{
            Client.selectAll().forEach{
                result.add(GetClient(
                 it[Client.id].value,
                 it[Client.login],
                 it[Client.creationDate].toString()
                ))
            }
        }
        return result
    }

    fun getAccounts(login: String): ArrayList<GetAccount> {
        val result = arrayListOf<GetAccount>()
        transaction {
            Account.select{Account.owner_id eq getByLogin(login).id}
                    .forEach {
                        result.add(GetAccount(
                                it[Account.id].value,
                                it[Account.number],
                                it[Account.balance],
                                it[Account.owner_id]
                        ))
            }
        }
        return result
    }

    fun getCards(account: String): ArrayList<GetCard> {
        val result = arrayListOf<GetCard>()
        transaction{
            Card.select {Card.ownerAccountID eq getAccountByNumber(account).id }
                    .forEach{
                        result.add(GetCard(
                         it[Card.number],
                         it[Card.creationDate].toString(),
                         it[Card.expirationDate].toString(),
                         it[Card.cvc],
                         it[Card.ownerAccountID],
                         it[Card.pin],
                         it[Card.uuid]
                        ))
                    }
        }
        return result
    }

    fun getTransactionByID(transaction: Int): GetTransaction {
        val result = GetTransaction()
        //TODO: add target by account number
        transaction {
            Bank_Transaction.select{
                (Bank_Transaction.id eq transaction)
            }.forEach{
                result.id = it[Bank_Transaction.id].value
                result.from = it[Bank_Transaction.from_account]
                result.to = it[Bank_Transaction.to_account]
                result.money = it[Bank_Transaction.money]
                result.type_id = it[Bank_Transaction.type]
                result.title = it[Bank_Transaction.title]
                result.orderDate = it[Bank_Transaction.order_date].toString()
                result.type = getTransactionType(it[Bank_Transaction.type])
            }
        }
        return result
    }

    fun getUserDetails(login: String): GetClientDetails {
        val result = GetClientDetails()
        transaction{
            Client_Details.select{ Client_Details.id eq getUserByLogin(login).id }
                    .forEach {
                        result.id = it[Client_Details.id].value
                        result.name = it[Client_Details.name]
                        result.surname = it[Client_Details.surname]
                        result.avatar_filepath = it[Client_Details.avatar_filepath]
                    }
        }

        return result
    }

    fun getTransactionType(type_id: Int): GetTransactionType {
        val result = GetTransactionType()
        transaction{
            Type.select{ Type.id eq type_id }
                    .forEach{
                        result.id = it[Type.id].value
                        result.abbr = it[Type.abbr]
                        result.name = it[Type.name]
                    }
        }

        return result


    /**
    * DEVELOPER ONLY - DELETE CLIENTS
    * */
    fun deleteAllClients(){
        transaction {
            Client.deleteAll()
        }
    }

    fun deleteClient(login: String){
        transaction {
            Client.deleteWhere { Client.login like login }
        }
    }

    /**
     * DEVELOPER ONLY - DELETE ALL ACCOUNTS
     * */
    fun deleteAllAccounts(){
        transaction {
            Account.deleteAll()
        }
    }
    fun deleteAccount(number: String){
        transaction {
            Account.deleteWhere { Account.number like number }
        }
    }

    fun deleteAllBank_Transactions(){
        transaction {
            Bank_Transaction.deleteAll()
        }
    }
    fun deleteBank_Transaction(id: String){
        val idInt = id.toInt()
        transaction {
            Bank_Transaction.deleteWhere { Bank_Transaction.id eq idInt }
        }
    }

    fun deleteAllCards(){
        transaction {
            Card.deleteAll()
        }
    }

    fun deleteCard(uuid: String){
        transaction {
            Card.deleteWhere { Card.uuid like uuid }
        }
    }

    fun deleteAll(){
        transaction {
            Card.deleteAll()
            Account.deleteAll()
            Client.deleteAll()
            Bank_Transaction.deleteAll()


        }

    }
}



