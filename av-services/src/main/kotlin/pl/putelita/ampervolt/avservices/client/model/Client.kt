package pl.putelita.ampervolt.avservices.client.model

import org.hibernate.annotations.Type
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "client")
class Client {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Column(name = "uuid")
    @Type(type="uuid-char")
    var uuid: UUID? = UUID.randomUUID()

    @Column(name = "login")
    var login: String? = null

    @Column(name = "password")
    var password: String? = null

    @Column(name = "creation_date")
    val creation_date: LocalDateTime = LocalDateTime.now()

    @Column(name = "name")
    var name: String? = null

    @Column(name = "second_name")
    var second_name: String? = null

    @Column(name = "surname")
    var surname: String? = null

    @Column(name = "removed")
    var removed: Boolean = false

    @Column(name = "remove_date")
    var removeDate: LocalDateTime? = null
}