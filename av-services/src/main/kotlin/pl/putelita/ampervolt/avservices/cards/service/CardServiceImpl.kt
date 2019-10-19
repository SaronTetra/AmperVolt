package pl.putelita.ampervolt.avservices.cards.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pl.putelita.ampervolt.avservices.cards.exception.CardNotFoundException
import pl.putelita.ampervolt.avservices.cards.exception.CardNumberExistsException
import pl.putelita.ampervolt.avservices.cards.model.Card
import pl.putelita.ampervolt.avservices.cards.repository.CardRepository
import java.util.*

@Service
class CardServiceImpl @Autowired constructor(private val cardRepository: CardRepository): CardService {

    override fun getAll(): MutableList<Card> {
        return cardRepository.findAll()
    }

    override fun getByUUID(uuid: UUID): Card {
        return cardRepository.findByUuid(uuid) ?: throw CardNotFoundException()
}

    override fun create(card: Card): Card {
        if (cardRepository.findByNumber(card.number) != null) {
            throw CardNumberExistsException()
        }
        return cardRepository.save(card)
    }

    override fun update(uuid: UUID, card: Card): Card {
        var cardDB: Card = cardRepository.findByUuid(uuid) ?: throw CardNotFoundException()
        if (!cardDB.number.equals(card.number)) {
            throw CardNumberExistsException()
        }
        cardDB.number = card.number
        cardDB.cvc = card.cvc
        cardDB.pin = card.pin
        cardDB.accountId = card.accountId
        cardDB.removed = card.removed
        cardDB.blocked = card.blocked
        return cardRepository.save(cardDB)
    }

    override fun deleteOrUpdate(uuid: UUID) {
        var cardDB: Card = cardRepository.findByUuid(uuid) ?: throw CardNotFoundException()
        if (cardDB.removed == false || cardDB.removed == null) {
            cardDB.removed = true
        }
        cardRepository.save(cardDB)
    }

    override fun blockOrUpdate(uuid: UUID) {
        var cardDB: Card = cardRepository.findByUuid(uuid) ?: throw CardNotFoundException()
        if (cardDB.blocked == false || cardDB.blocked == null) {
            cardDB.blocked = true
        }
        cardRepository.save(cardDB)
    }
}