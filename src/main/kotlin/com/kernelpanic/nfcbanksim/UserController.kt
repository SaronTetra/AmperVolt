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
    val counter = AtomicLong()
    val BANK_IDENTIFIER: String = "6661987164"



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
        db.signUp(signUp.name, signUp.surname, signUp.login, signUp.password)
        return ResponseEntity(HttpStatus.CREATED) //TODO error
    }


    @GetMapping("users/{login}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getUserByLogin(@PathVariable login: String) : GetClient {
        return db.getByLogin(login)
    }

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

    /**
     * DEVELOPER ONLY - DELETE ALL ACCOUNTS
     * */

    @DeleteMapping("/dev/delete-accounts")
    @ResponseBody
    fun deleteAllAccounts(): ResponseEntity<Unit>{
        db.deleteAllAccounts()
        return ResponseEntity(HttpStatus.OK)
    }

    /**
     * DEVELOPER ONLY - DELETE ALL CLIENT DETAILS
     * */
    @DeleteMapping("/dev/delete-client-details")
    @ResponseBody
    fun deleteAllClientDetails(): ResponseEntity<Unit>{
        db.deleteAllClient_Details()
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

    /**
     * DEVELOPER ONLY - DELETE ALL CARDS
     * */
    @DeleteMapping("/dev/delete-cards")
    @ResponseBody
    fun deleteAllCards(): ResponseEntity<Unit>{
        db.deleteAllCards()
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


    @PutMapping("users/{login}/{account}/transaction")
    @ResponseBody
    fun transaction(@PathVariable login: String, @PathVariable account: String,
                    @RequestBody transactionJSON: TransactionJSON): ResponseEntity<Unit>{

        db.doTransaction(account, transactionJSON.destinationAcc, transactionJSON.money, transactionJSON.title)
        return ResponseEntity(HttpStatus.OK) //TODO error
    }
//
//
//    @PostMapping("/users/{login}/add-card",
//            consumes = [MediaType.APPLICATION_JSON_VALUE])
//    @ResponseBody
//    fun addCard(@PathVariable login:String, @RequestBody newCard: AddCard): ResponseEntity<Unit>{
//        val cardNumber = generateCardNumber()
//        println("Number: ${cardNumber}, cvc: ${newCard.cvc}, PIN: ${newCard.pin}")
//        db.addCard(cardNumber, newCard.cvc, login, newCard.pin)
//        return ResponseEntity(HttpStatus.CREATED) //TODO error
//    }
//
@CrossOrigin(origins = ["https://ampervolt.putelita.pl","https://kernelpanic.putelita.pl"])
    @GetMapping("/users/{login}/{account}/transactions", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getTransactions(@PathVariable account: String): ArrayList<GetTransactions>{
        return db.getTransactions(account)
    }

    @CrossOrigin(origins = ["https://ampervolt.putelita.pl","https://kernelpanic.putelita.pl"])
    @GetMapping("/card/{uuid}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getCardFromUUID(@PathVariable uuid: String): GetCard {
        return db.getCardByUUID(uuid)
    }

    @CrossOrigin(origins = ["https://ampervolt.putelita.pl","https://kernelpanic.putelita.pl"])
    @PostMapping("/users/{account}/pay",
            consumes = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun cardPayment(@PathVariable account:String, @RequestBody cardTrans: CardTransaction): ResponseEntity<Unit>{
        db.cardTransaction(cardTrans.uuid, account, cardTrans.money, cardTrans.title)
        return ResponseEntity(HttpStatus.OK) //TODO error
    }
}
