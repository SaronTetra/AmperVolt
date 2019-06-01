package com.kernelpanic.nfcbanksim

import com.kernelpanic.nfcbanksim.Cards.generateCardNumber
import com.kernelpanic.nfcbanksim.Database.BankDatabase
import com.kernelpanic.nfcbanksim.GET.GetCard
import com.kernelpanic.nfcbanksim.GET.GetClient
import com.kernelpanic.nfcbanksim.GET.GetTransactions
import com.kernelpanic.nfcbanksim.POST_PUT.AddCard
import com.kernelpanic.nfcbanksim.POST_PUT.PutMoneyJSON
import com.kernelpanic.nfcbanksim.POST_PUT.SignUp
import com.kernelpanic.nfcbanksim.POST_PUT.TransactionJSON
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


    @GetMapping("users/{login}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getUserByLogin(@PathVariable login: String) : GetClient {
        return db.getByLogin(login)
    }

    @DeleteMapping("/users/{login}/delete-account")
    @ResponseBody
    fun deleteAccount(@PathVariable login: String): ResponseEntity<Unit>{

        db.deleteAccount(login)
        return ResponseEntity(HttpStatus.OK) //TODO error
    }


    @PutMapping("/put-money")
    @ResponseBody
    fun putMoney(@RequestBody putMoneyJSON: PutMoneyJSON): ResponseEntity<Unit>{
        db.putMoney(putMoneyJSON.login, putMoneyJSON.money, putMoneyJSON.title)
        return ResponseEntity(HttpStatus.OK) //TODO error
    }


    @PutMapping("/transaction")
    @ResponseBody
    fun transaction(@RequestBody transactionJSON: TransactionJSON): ResponseEntity<Unit>{

        db.doTransaction(transactionJSON.login, transactionJSON.destinationLogin, transactionJSON.money, transactionJSON.title)
        return ResponseEntity(HttpStatus.OK) //TODO error
    }


     @PostMapping("/users/{login}/add-card",
            consumes = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun addCard(@PathVariable login:String, @RequestBody newCard: AddCard): ResponseEntity<Unit>{
        val cardNumber = generateCardNumber()
        println("Number: ${cardNumber}, cvc: ${newCard.cvc}, PIN: ${newCard.pin}")
        db.addCard(cardNumber, newCard.cvc, login, newCard.pin)
        return ResponseEntity(HttpStatus.CREATED) //TODO error
    }

    @GetMapping("/users/{login}/transactions", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getTransactions(@PathVariable login: String): ArrayList<GetTransactions>{
        return db.getTransactions(login)
    }

    @GetMapping("/card/{uuid}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getCardFromUUID(@PathVariable uuid: String): GetCard {
        return db.getCardByUUID(uuid)
    }
}
