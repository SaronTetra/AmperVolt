package pl.putelita.ampervolt.avservices.account.service

import pl.putelita.ampervolt.avservices.account.model.Account
import java.util.*

interface AccountService {

    fun getAll(): MutableList<Account>

    fun getByUuid(uuid: UUID): Account

    fun create(account: Account): Account

    fun deleteOrUpdate(uuid: UUID)
}