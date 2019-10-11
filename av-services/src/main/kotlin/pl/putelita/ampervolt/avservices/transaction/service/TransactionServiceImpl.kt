package pl.putelita.ampervolt.avservices.transaction.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pl.putelita.ampervolt.avservices.account.model.Account
import pl.putelita.ampervolt.avservices.account.service.AccountService
import pl.putelita.ampervolt.avservices.transaction.exception.TransactionMoneyLessThanZeroException
import pl.putelita.ampervolt.avservices.transaction.exception.TransactionNotFoundException
import pl.putelita.ampervolt.avservices.transaction.model.Transaction
import pl.putelita.ampervolt.avservices.transaction.repository.TransactionRepository
import java.util.*
import javax.security.auth.login.AccountNotFoundException

@Service
class TransactionServiceImpl @Autowired constructor(private val transactionRepository: TransactionRepository,
                                                    private val accountService: AccountService): TransactionService {

    override fun getAll(): MutableList<Transaction> {
        return transactionRepository.findAll()
    }

    override fun getByUuid(uuid: UUID): Transaction {
        return transactionRepository.findByUuid(uuid) ?: throw TransactionNotFoundException()
    }

    override fun transaction(transaction: Transaction): Transaction {
        val fromAccountDB: Account = accountService.findByNumber(transaction.fromAccount?.number) ?: throw AccountNotFoundException()
        val toAccountDB: Account = accountService.findByNumber(transaction.toAccount?.number) ?: throw AccountNotFoundException()
        if (transaction.money < 0) {throw TransactionMoneyLessThanZeroException()}
        fromAccountDB.balance -= transaction.money
        toAccountDB.balance += transaction.money
        transaction.fromAccount=fromAccountDB
        transaction.toAccount=toAccountDB
        return transactionRepository.save(transaction)
    }

    override fun bankPayment(transaction: Transaction): Transaction {
        val toAccountDB: Account = accountService.findByNumber(transaction.toAccount?.number) ?: throw AccountNotFoundException()
        if (transaction.money < 0) {throw TransactionMoneyLessThanZeroException()}
        toAccountDB.balance += transaction.money
        transaction.toAccount=toAccountDB
        return transactionRepository.save(transaction)
    }

}