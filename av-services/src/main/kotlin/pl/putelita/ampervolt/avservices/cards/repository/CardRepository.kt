package pl.putelita.ampervolt.avservices.cards.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import pl.putelita.ampervolt.avservices.cards.model.Card
import pl.putelita.ampervolt.avservices.client.model.Client
import java.util.*

@Repository
interface CardRepository: JpaRepository<Card, Long> {

    fun findByUuid(uuid: UUID): Card?

    fun findByNumber(number: String?): Card?

}
