package pl.putelita.ampervolt.avservices.transaction.model

import javax.persistence.*


@Entity
@Table(name = "transaction_type")
class TransactionType {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Column(name = "abbr")
    val abbr: String? = null

    @Column(name = "name")
    val name: String? = null
}