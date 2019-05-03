package com.kernelpanic.nfcbanksim

import com.fasterxml.jackson.databind.ser.std.StdJdkSerializers
import com.kernelpanic.nfcbanksim.Database.BankDatabase
import com.kernelpanic.nfcbanksim.POST.SignUp
import org.springframework.http.MediaType
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicLong

@RestController
class UserController {
    val db = BankDatabase()
    val counter = AtomicLong()

    @GetMapping("/user")
    fun hello(@RequestParam(value = "name", defaultValue = "World") name : String) =
            User(counter.incrementAndGet(), "Hello, $name")

    @GetMapping("/all")
    fun allUsers() : User { //TODO arraylist
        db.printAllClients()
        return User(1, "a")
    }

    @PostMapping("/signup",
            consumes =
            [MediaType.APPLICATION_JSON_VALUE])
    fun signUp(
            @RequestBody signUp: SignUp){

        println("Name: ${signUp._name}, Login: ${signUp._login}, Password: ${signUp._password}")
        db.signUp(signUp._name, signUp._login, signUp._password)
    }



    //TODO wplata z banku (może id 0?)
    //TODO Sign up
    //TODO Delete account(somehow preserve in history)
    //TODO Transaction
    //TODO New Card - maciej
    //TODO Login
    //TODO CardCost
    //TODO Deposit
    //TODO All transactions for user; maybe some filters later?

}