package com.kernelpanic.nfcbanksim

import com.kernelpanic.nfcbanksim.Database.BankDatabase
import com.kernelpanic.nfcbanksim.GET.GetClient
import com.kernelpanic.nfcbanksim.POST.SignUp
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicLong

@RestController
class UserController {
    val db = BankDatabase()
    val counter = AtomicLong()



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



    @PostMapping("/signup",
            consumes = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun signUp(@RequestBody signUp: SignUp): ResponseEntity<Unit>{

        println("Name: ${signUp.name}, Login: ${signUp.login}, Password: ${signUp.password}")
        db.signUp(signUp.name, signUp.login, signUp.password)
        return ResponseEntity(HttpStatus.CREATED) //TODO error
    }


    @GetMapping("/{login}")
    fun getUserByLogin(@PathVariable login: String) : GetClient {
        return db.getByLogin(login)
    }

    @DeleteMapping("/users/{id}/delete-account")
    fun deleteAccount(){

    }

}