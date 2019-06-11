package com.kernelpanic.nfcbanksim

import com.kernelpanic.nfcbanksim.Database.BankDatabase
import com.kernelpanic.nfcbanksim.POST_PUT.Str
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController


@RestController
class DeleteController {
    val db = BankDatabase()

    /**
     * DEVELOPER ONLY - DELETE ALL CLIENTS
     * */
    @DeleteMapping("/dev/delete-clients")
    @ResponseBody
    fun deleteAllClients(): ResponseEntity<Unit> {
        db.deleteAllClients()
        return ResponseEntity(HttpStatus.OK)
    }

    @DeleteMapping("/dev/delete-client")
    @ResponseBody
    fun deleteClient(@RequestBody login: Str): ResponseEntity<Unit> {
        db.deleteClient(login.data)
        return ResponseEntity(HttpStatus.OK)
    }



    /**
     * DEVELOPER ONLY - DELETE ALL ACCOUNTS
     * */

    @DeleteMapping("/dev/delete-accounts")
    @ResponseBody
    fun deleteAllAccounts(): ResponseEntity<Unit> {
        db.deleteAllAccounts()
        return ResponseEntity(HttpStatus.OK)
    }
    @DeleteMapping("/dev/delete-account")
    @ResponseBody
    fun deleteAccount(@RequestBody login: Str): ResponseEntity<Unit> {
        db.deleteAccount(login.data)
        return ResponseEntity(HttpStatus.OK)
    }

    /**
     * DEVELOPER ONLY - DELETE ALL BANK TRANSACTIONS
     * */
    @DeleteMapping("/dev/delete-bank-transactions")
    @ResponseBody
    fun deleteAllBankTransactions(): ResponseEntity<Unit> {
        db.deleteAllBank_Transactions()
        return ResponseEntity(HttpStatus.OK)
    }

    @DeleteMapping("/dev/delete-transaction")
    @ResponseBody
    fun deleteTransaction(@RequestBody login: Str): ResponseEntity<Unit> {
        db.deleteBank_Transaction(login.data)
        return ResponseEntity(HttpStatus.OK)
    }

    /**
     * DEVELOPER ONLY - DELETE ALL CARDS
     * */
    @DeleteMapping("/dev/delete-cards")
    @ResponseBody
    fun deleteAllCards(): ResponseEntity<Unit> {
        db.deleteAllCards()
        return ResponseEntity(HttpStatus.OK)
    }

    @DeleteMapping("/dev/delete-card")
    @ResponseBody
    fun deleteCard(@RequestBody login: Str): ResponseEntity<Unit> {
        db.deleteCard(login.data)
        return ResponseEntity(HttpStatus.OK)
    }

    /**
     * DEVELOPER ONLY - DELETE ALL CARDS
     * */
    @DeleteMapping("/dev/delete-all")
    @ResponseBody
    fun deleteAll(): ResponseEntity<Unit> {
        db.deleteAll()
        return ResponseEntity(HttpStatus.OK)
    }

}