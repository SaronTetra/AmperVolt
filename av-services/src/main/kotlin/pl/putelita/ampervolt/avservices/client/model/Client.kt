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
    val uuid: UUID? = null

    @Column(name = "login")
    val login: String? = null

    @Column(name = "password")
    val password: String? = null

    @Column(name = "creation_date")
    val creation_date: LocalDateTime? = null

    @Column(name = "name")
    val name: String? = null

    @Column(name = "second_name")
    val second_name: String? = null

    @Column(name = "surname")
    val surname: String? = null
}