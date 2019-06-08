package com.kernelpanic.nfcbanksim

import com.kernelpanic.nfcbanksim.Cards.generateCardNumber
import com.kernelpanic.nfcbanksim.Database.BankDatabase
import com.kernelpanic.nfcbanksim.POST_PUT.AddCard
import com.kernelpanic.nfcbanksim.POST_PUT.CardTransaction
import com.kernelpanic.nfcbanksim.POST_PUT.SignUp
import com.kernelpanic.nfcbanksim.POST_PUT.TransactionJSON
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import java.util.concurrent.ThreadLocalRandom

@RestController

class PostController {
    val db = BankDatabase()
    val BANK_IDENTIFIER: String = "6661987164"


//    CLIENT
    @PostMapping("/signup",
            consumes = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun signUp(@RequestBody signUp: SignUp): ResponseEntity<Unit> {

        println("Name: ${signUp.name}, Login: ${signUp.login}, Password: ${signUp.password}")
        db.signUp(signUp.name, signUp.surname, signUp.login, signUp.password)
        return ResponseEntity(HttpStatus.CREATED) //TODO error
    }


//    ACCOUNT
    @PostMapping("/users/{login}/new-account")
    fun newAccount(@PathVariable login: String ): ResponseEntity<Unit>{

        //generate
        //TODO find from login id
        val gen = Random()

        var generatedNumber = ThreadLocalRandom
                .current()
                .nextLong(1000000000000000, 9999999999999999)
                .toString()
        generatedNumber = BANK_IDENTIFIER+generatedNumber
        db.createAccount(login, generatedNumber)

        return ResponseEntity(HttpStatus.CREATED) //TODO error
    }


//    CARD
    @PostMapping("/users/{login}/{account}/add-card",
            consumes = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun addCard(@PathVariable login:String, @PathVariable account: String, @RequestBody newCard: AddCard): ResponseEntity<Unit>{
        val cardNumber = generateCardNumber()
        println("Number: $cardNumber, cvc: ${newCard.cvc}, PIN: ${newCard.pin}")
        db.addCard(cardNumber, newCard.cvc, account, newCard.pin)
        return ResponseEntity(HttpStatus.CREATED) //TODO error
    }

//    TRANSACTION
    @PutMapping("users/{login}/{account}/transaction")
    @ResponseBody
    fun transaction(@PathVariable login: String, @PathVariable account: String,
                    @RequestBody transactionJSON: TransactionJSON): ResponseEntity<Unit>{

        db.doTransaction(account, transactionJSON.destinationAcc, transactionJSON.money, transactionJSON.title)
        return ResponseEntity(HttpStatus.OK) //TODO error
    }


//    CARD TRANSACTION
    @CrossOrigin(origins = ["https://ampervolt.putelita.pl","https://kernelpanic.putelita.pl"])
    @PostMapping("/users/{account}/pay",
            consumes = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun cardPayment(@PathVariable account:String, @RequestBody cardTrans: CardTransaction): ResponseEntity<Unit>{
        db.cardTransaction(cardTrans.uuid, account, cardTrans.money, cardTrans.title)
        return ResponseEntity(HttpStatus.OK) //TODO error
    }


}