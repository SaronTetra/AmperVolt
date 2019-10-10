package pl.putelita.ampervolt.avservices.account.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import pl.putelita.ampervolt.avservices.account.model.Account
import java.util.*

@Repository
interface AccountRepository: JpaRepository<Account, Long> {

    fun findByUuid(uuid: UUID): Account?

    fun findByNumberIgnoreCase(number: String?): Account?
}