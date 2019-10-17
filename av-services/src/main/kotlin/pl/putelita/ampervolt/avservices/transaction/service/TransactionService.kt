package pl.putelita.ampervolt.avservices.transaction.service

import pl.putelita.ampervolt.avservices.transaction.model.Transaction
import java.util.*

interface TransactionService {

    fun getAll(): MutableList<Transaction>

    fun getByUuid(uuid: UUID): Transaction

    fun transaction(transaction: Transaction): Transaction

    fun bankPayment(transaction: Transaction): Transaction
}