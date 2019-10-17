package pl.putelita.ampervolt.avservices.account.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pl.putelita.ampervolt.avservices.account.exception.AccountNotFoundException
import pl.putelita.ampervolt.avservices.account.exception.AccountNumberExistsException
import pl.putelita.ampervolt.avservices.account.model.Account
import pl.putelita.ampervolt.avservices.account.repository.AccountRepository
import java.time.LocalDateTime
import java.util.*

@Service
class AccountServiceImpl @Autowired constructor (private val accountRepository: AccountRepository): AccountService {

    override fun getAll(): MutableList<Account> {
        return accountRepository.findAll()
    }

    override fun getByUuid(uuid: UUID): Account {
        return accountRepository.findByUuid(uuid) ?: throw AccountNotFoundException()
    }

    override fun create(account: Account): Account {
        if (accountRepository.findByNumberIgnoreCase(account.number) != null) {
            throw AccountNumberExistsException()
        }
        return accountRepository.save(account)
    }

    override fun deleteOrUpdate(uuid: UUID) {
        var accountDB: Account = accountRepository.findByUuid(uuid) ?: throw AccountNotFoundException()
        if (accountDB.removed == false || accountDB.removed == null) {
            accountDB.removed = true
            accountDB.removeDate = LocalDateTime.now()
        }
        accountRepository.save(accountDB)
    }

    override fun findByNumber(number: String?): Account? {
        return accountRepository.findByNumber(number) ?: throw AccountNotFoundException()
    }

    override fun findByUuid(uuid: UUID?): Account {
        return accountRepository.findByUuid(uuid) ?: throw AccountNotFoundException()
    }

    override fun save(account: Account): Account {
        return accountRepository.save(account)
    }
}