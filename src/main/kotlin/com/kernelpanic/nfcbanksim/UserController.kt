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






    @PutMapping("/put-money")
    @ResponseBody
    fun putMoney(@RequestBody putMoneyJSON: PutMoneyJSON): ResponseEntity<Unit>{
        db.putMoney(putMoneyJSON.account, putMoneyJSON.money)
        return ResponseEntity(HttpStatus.OK) //TODO error
    }

}
