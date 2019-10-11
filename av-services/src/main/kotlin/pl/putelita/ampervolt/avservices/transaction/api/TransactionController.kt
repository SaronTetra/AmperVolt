package pl.putelita.ampervolt.avservices.transaction.api

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import pl.putelita.ampervolt.avservices.transaction.model.Transaction
import pl.putelita.ampervolt.avservices.transaction.service.TransactionService
import java.util.*

@RestController
@RequestMapping("transaction")
class TransactionController @Autowired constructor(private val transactionService: TransactionService) {

    @GetMapping
    fun getAll(): MutableList<Transaction> {
        return transactionService.getAll()
    }

    @GetMapping("{uuid}")
    fun getByUuid(@PathVariable uuid: UUID): Transaction {
        return transactionService.getByUuid(uuid)
    }

    @PostMapping
    fun transaction(@RequestBody transaction: Transaction): Transaction {
        return transactionService.transaction(transaction)
    }

    @PostMapping("bankPayment")
    fun bankPayment(@RequestBody transaction: Transaction): Transaction {
        return transactionService.bankPayment(transaction)
    }
}