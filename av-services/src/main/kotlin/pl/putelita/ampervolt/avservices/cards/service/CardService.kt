package pl.putelita.ampervolt.avservices.cards.service

import org.springframework.stereotype.Repository
import pl.putelita.ampervolt.avservices.cards.model.Card
import java.util.*

interface CardService {

    fun getAll(): MutableList<Card>

    fun getByUUID(uuid: UUID): Card

    fun create(card: Card): Card

    fun update(uuid: UUID, card: Card): Card

    fun deleteOrUpdate(uuid: UUID)

    fun blockOrUpdate(uuid: UUID)
}