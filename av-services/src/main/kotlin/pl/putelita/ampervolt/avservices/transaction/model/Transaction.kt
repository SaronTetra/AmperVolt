package pl.putelita.ampervolt.avservices.transaction.model

import org.hibernate.annotations.Type
import pl.putelita.ampervolt.avservices.account.model.Account
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "transaction")
class Transaction {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Column(name = "uuid")
    @Type(type="uuid-char")
    var uuid: UUID? = UUID.randomUUID()

    @Column(name = "order_date")
    var orderDate: LocalDateTime = LocalDateTime.now()

    @Column(name = "money")
    var money: Double = 0.0

    @ManyToOne(cascade=[CascadeType.ALL])
    @JoinColumn(name = "from_account")
    var fromAccount: Account? = null

    @ManyToOne(cascade=[CascadeType.ALL])
    @JoinColumn(name = "to_account")
    var toAccount: Account? = null

    @Column(name = "title")
    var title: String? = null

    @ManyToOne
    @JoinColumn(name = "type_id")
    var type: TransactionType? = null
}