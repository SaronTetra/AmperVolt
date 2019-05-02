package com.kernelpanic.nfcbanksim

import com.fasterxml.jackson.databind.ser.std.StdJdkSerializers
import com.kernelpanic.nfcbanksim.Database.BankDatabase
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicLong

@RestController
class UserController {

    val counter = AtomicLong()

    @GetMapping("/user")
    fun hello(@RequestParam(value = "name", defaultValue = "World") name : String) =
            User(counter.incrementAndGet(), "Hello, $name")

    @GetMapping("/all")
    fun allUsers() : User {
        val db = BankDatabase()
        db.printAllClients()
        return User(1, "a")
    }


}