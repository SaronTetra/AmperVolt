package pl.putelita.ampervolt.avservices.client.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import pl.putelita.ampervolt.avservices.client.model.Client

@Repository
interface ClientRepository: JpaRepository<Client, Long> {
}