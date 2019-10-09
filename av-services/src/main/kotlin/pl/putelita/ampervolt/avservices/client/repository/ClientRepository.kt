package pl.putelita.ampervolt.avservices.client.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import pl.putelita.ampervolt.avservices.client.model.Client
import java.util.*

@Repository
interface ClientRepository: JpaRepository<Client, Long> {

    fun findByUuid(uuid: UUID): Client?

    fun findByLoginIgnoreCase(login: String?): Client?

    fun findAllByRemovedIsFalse(): MutableList<Client>
}