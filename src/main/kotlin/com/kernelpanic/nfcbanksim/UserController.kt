package com.kernelpanic.nfcbanksim

import com.kernelpanic.nfcbanksim.Cards.generateCardNumber
import com.kernelpanic.nfcbanksim.Database.BankDatabase
import com.kernelpanic.nfcbanksim.GET.GetCard
import com.kernelpanic.nfcbanksim.GET.GetClient
import com.kernelpanic.nfcbanksim.GET.GetTransactions
import com.kernelpanic.nfcbanksim.POST_PUT.*
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*
import java.util.concurrent.ThreadLocalRandom
import java.util.concurrent.atomic.AtomicLong

@RestController
class UserController {
    val db = BankDatabase()

//    @GetMapping("/user")
//    fun hello(@RequestParam(value = "name", defaultValue = "World") name : String) =
//            User(counter.incrementAndGet(), "Hello, $name")
//
//
//
//    @GetMapping("/all")
//    fun allUsers() : User { //TODO arraylist
//        db.printAllClients()
//        return User(1, "a")
//    }








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
