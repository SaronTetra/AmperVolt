package pl.putelita.ampervolt.avservices.cards.model

import org.hibernate.annotations.Type
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name="card")
class Card {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Column(name="uuid")
    @Type(type="uuid-char")
    var uuid: UUID? = UUID.randomUUID()

    @Column(name="creation_date")
    val creationDate: LocalDateTime = LocalDateTime.now()

    @Column(name="expiration_date")
    val expirationDate: LocalDateTime = LocalDateTime.now()

    @Column(name="number")
    var number: String? = null

    @Column(name="cvc")
    var cvc: String? = null

    @Column(name="pin")
    var pin: String? = null

    @Column(name="account_id")
    var accountId: Int? = null

    @Column(name = "removed")
    var removed: Boolean = false

    @Column(name = "blocked")
    var blocked: Boolean = false
}