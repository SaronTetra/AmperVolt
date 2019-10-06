package com.kernelpanic.nfcbanksim

import com.kernelpanic.nfcbanksim.Database.BankDatabase
import com.kernelpanic.nfcbanksim.POST_PUT.Str
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
class DeleteController {
    val db = BankDatabase()

    /**
     * DEVELOPER ONLY - DELETE ALL CLIENTS
     * */
    @DeleteMapping("/users")
    @ResponseBody
    fun deleteAllClients(): ResponseEntity<Unit> {
        db.deleteAllClients()
        return ResponseEntity(HttpStatus.OK)
    }

    @DeleteMapping("/users/{login}")
    @ResponseBody
    fun deleteClient(@PathVariable login: String): ResponseEntity<Unit> {
        db.deleteClient(login)
        return ResponseEntity(HttpStatus.OK)
    }



    /**
     * DEVELOPER ONLY - DELETE ALL ACCOUNTS
     * */

    @DeleteMapping("/accounts")
    @ResponseBody
    fun deleteAllAccounts(): ResponseEntity<Unit> {
        db.deleteAllAccounts()
        return ResponseEntity(HttpStatus.OK)
    }
    @DeleteMapping("/users/{login}/{account}")
    @ResponseBody
    fun deleteAccount(@PathVariable account: String): ResponseEntity<Unit> {
        db.deleteAccount(account)
        return ResponseEntity(HttpStatus.OK)
    }

    /**
     * DEVELOPER ONLY - DELETE ALL BANK TRANSACTIONS
     * */
    @DeleteMapping("/bank-transactions")
    @ResponseBody
    fun deleteAllBankTransactions(): ResponseEntity<Unit> {
        db.deleteAllBank_Transactions()
        return ResponseEntity(HttpStatus.OK)
    }

    @DeleteMapping("/users/{login}/{account}/transactions")
    @ResponseBody
    fun deleteTransaction(@RequestBody login: Str): ResponseEntity<Unit> {
        db.deleteBank_Transaction(login.data)
        return ResponseEntity(HttpStatus.OK)
    }

    /**
     * DEVELOPER ONLY - DELETE ALL CARDS
     * */
    @DeleteMapping("/cards")
    @ResponseBody
    fun deleteAllCards(): ResponseEntity<Unit> {
        db.deleteAllCards()
        return ResponseEntity(HttpStatus.OK)
    }

    @DeleteMapping("/card/{uuid}")
    @ResponseBody
    fun deleteCard(@RequestBody uuid: String): ResponseEntity<Unit> {
        db.deleteCard(uuid)
        return ResponseEntity(HttpStatus.OK)
    }

    /**
     * DEVELOPER ONLY - DELETE ALL CARDS
     * */
    @DeleteMapping("/delete-all")
    @ResponseBody
    fun deleteAll(): ResponseEntity<Unit> {
        db.deleteAll()
        return ResponseEntity(HttpStatus.OK)
    }

}