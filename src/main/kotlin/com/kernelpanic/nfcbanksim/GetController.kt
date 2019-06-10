package com.kernelpanic.nfcbanksim

import com.kernelpanic.nfcbanksim.Database.BankDatabase
import com.kernelpanic.nfcbanksim.GET.*
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class GetController {
    val db = BankDatabase()


//    CLIENT
    @GetMapping("users/{login}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getUserByLogin(@PathVariable login: String) : GetClient {
        return db.getByLogin(login)
    }


//    CLIENTS
    @GetMapping("users/", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getUserByLogin() : ArrayList<GetClient> {
        return db.getUsers()
    }


//    CLIENT DETAILS
    @GetMapping("users/{login}/details", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getUserDetails(@PathVariable login: String) : GetClientDetails {
        return db.getUserDetails(login)
    }

//    ACCOUNT
    @GetMapping("users/{login}/{account}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAccountByNumber(@PathVariable login: String, @PathVariable account:String) : GetAccount {
        return db.getAccountByNumber(account)
    }

//    ACCOUNTS
   @GetMapping("users/{login}/accounts", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAccounts(@PathVariable login: String) : ArrayList<GetAccount> {
        return db.getAccounts(login)
    }

//    CARD
    @CrossOrigin(origins = ["https://ampervolt.putelita.pl","https://kernelpanic.putelita.pl"])
    @GetMapping("/card/{uuid}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getCardFromUUID(@PathVariable uuid: String): GetCard {
        return db.getCardByUUID(uuid)
    }


//    CARDS
    @CrossOrigin(origins = ["https://ampervolt.putelita.pl","https://kernelpanic.putelita.pl"])
    @GetMapping("/users/{login}/{account}/cards/", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getCards(@PathVariable login: String, @PathVariable account: String): ArrayList<GetCard> {
        return db.getCards(account)
    }

//    TRANSACTION
    @CrossOrigin(origins = ["https://ampervolt.putelita.pl","https://kernelpanic.putelita.pl"])
    @GetMapping("/users/{login}/{account}/{transaction}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getTransactionByID(@PathVariable account: String, @PathVariable transaction: Int): GetTransaction{
        return db.getTransactionByID(transaction)
}

//    TRANSACTIONS
    @CrossOrigin(origins = ["https://ampervolt.putelita.pl","https://kernelpanic.putelita.pl"])
    @GetMapping("/users/{login}/{account}/transactions", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getTransactions(@PathVariable account: String): ArrayList<GetTransaction>{
        return db.getTransactions(account)
    }

}
