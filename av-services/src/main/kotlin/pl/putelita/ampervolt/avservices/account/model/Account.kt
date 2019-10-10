package pl.putelita.ampervolt.avservices.account.model

import org.hibernate.annotations.Type
import pl.putelita.ampervolt.avservices.client.model.Client
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "account")
class Account {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Column(name = "uuid")
    @Type(type="uuid-char")
    var uuid: UUID? = UUID.randomUUID()

    @Column(name = "number")
    var number: String? = null

    @Column(name = "balance")
    var balance: Double = 0.0

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    val ownerId: Client? = null

    @Column(name = "creation_date")
    val creationDate: LocalDateTime = LocalDateTime.now()

    @Column(name = "removed")
    var removed: Boolean = false

    @Column(name = "remove_date")
    var removeDate: LocalDateTime? = null
}