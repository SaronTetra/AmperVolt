package pl.putelita.ampervolt.avservices.cards.api

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import pl.putelita.ampervolt.avservices.cards.model.Card
import pl.putelita.ampervolt.avservices.cards.service.CardService
import pl.putelita.ampervolt.avservices.client.model.Client
import java.util.*

@RestController
@RequestMapping("card")
class CardController @Autowired constructor (private val cardService: CardService){

    @GetMapping
    fun getAll(): MutableList<Card> {
        return cardService.getAll()
    }

    @GetMapping("{uuid}")
    fun getByUuid(@PathVariable uuid: UUID): Card {
        return cardService.getByUUID(uuid)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody card: Card): Card {
        return cardService.create(card)
    }

    @PutMapping("{uuid}")
    fun update(@RequestBody card: Card, @PathVariable uuid: UUID): Card {
        return cardService.update(uuid, card)
    }

    @DeleteMapping("{uuid}")
    fun deleteOrUpdate(@PathVariable uuid: UUID) {
        cardService.deleteOrUpdate(uuid)
    }
}