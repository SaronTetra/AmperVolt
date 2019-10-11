package pl.putelita.ampervolt.avservices.transaction.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import pl.putelita.ampervolt.avservices.transaction.model.Transaction
import java.util.*

@Repository
interface TransactionRepository: JpaRepository<Transaction, Long> {

    fun findByUuid(uuid: UUID): Transaction?
}