package com.kernelpanic.nfcbanksim

import com.kernelpanic.nfcbanksim.Database.BankDatabase
import com.kernelpanic.nfcbanksim.POST_PUT.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController {
    val db = BankDatabase()

//
//    @DeleteMapping("/users/{login}/delete-account")
//    @ResponseBody
//    fun deleteAccount(@PathVariable login: String): ResponseEntity<Unit>{
//
//        db.deleteAccount(login)
//        return ResponseEntity(HttpStatus.OK) //TODO error
//    }
//
//

    /**
     * DEVELOPER ONLY - DELETE ALL CLIENTS
     * */
    @DeleteMapping("/dev/delete-clients")
    @ResponseBody
    fun deleteAllClients(): ResponseEntity<Unit>{
        db.deleteAllClients()
        return ResponseEntity(HttpStatus.OK)
    }

    @DeleteMapping("/dev/delete-client")
    @ResponseBody
    fun deleteClient(@RequestBody login: Str): ResponseEntity<Unit>{
        db.deleteClient(login.data)
        return ResponseEntity(HttpStatus.OK)
    }



    /**
     * DEVELOPER ONLY - DELETE ALL ACCOUNTS
     * */

    @DeleteMapping("/dev/delete-accounts")
    @ResponseBody
    fun deleteAllAccounts(): ResponseEntity<Unit>{
        db.deleteAllAccounts()
        return ResponseEntity(HttpStatus.OK)
    }
    @DeleteMapping("/dev/delete-account")
    @ResponseBody
    fun deleteAccount(@RequestBody login: Str): ResponseEntity<Unit>{
        db.deleteAccount(login.data)
        return ResponseEntity(HttpStatus.OK)
    }

    /**
     * DEVELOPER ONLY - DELETE ALL BANK TRANSACTIONS
     * */
    @DeleteMapping("/dev/delete-bank-transactions")
    @ResponseBody
    fun deleteAllBankTransactions(): ResponseEntity<Unit>{
        db.deleteAllBank_Transactions()
        return ResponseEntity(HttpStatus.OK)
    }

    @DeleteMapping("/dev/delete-transaction")
    @ResponseBody
    fun deleteTransaction(@RequestBody login: Str): ResponseEntity<Unit>{
        db.deleteBank_Transaction(login.data)
        return ResponseEntity(HttpStatus.OK)
    }

    /**
     * DEVELOPER ONLY - DELETE ALL CARDS
     * */
    @DeleteMapping("/dev/delete-cards")
    @ResponseBody
    fun deleteAllCards(): ResponseEntity<Unit>{
        db.deleteAllCards()
        return ResponseEntity(HttpStatus.OK)
    }

    @DeleteMapping("/dev/delete-card")
    @ResponseBody
    fun deleteCard(@RequestBody login: Str): ResponseEntity<Unit>{
        db.deleteCard(login.data)
        return ResponseEntity(HttpStatus.OK)
    }

    /**
     * DEVELOPER ONLY - DELETE ALL CARDS
     * */
    @DeleteMapping("/dev/delete-all")
    @ResponseBody
    fun deleteAll(): ResponseEntity<Unit>{
        db.deleteAll()
        return ResponseEntity(HttpStatus.OK)
    }




    @PutMapping("/put-money")
    @ResponseBody
    fun putMoney(@RequestBody putMoneyJSON: PutMoneyJSON): ResponseEntity<Unit>{
        db.putMoney(putMoneyJSON.account, putMoneyJSON.money)
        return ResponseEntity(HttpStatus.OK) //TODO error
    }

}
