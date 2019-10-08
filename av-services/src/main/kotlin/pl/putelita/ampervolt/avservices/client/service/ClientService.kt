package pl.putelita.ampervolt.avservices.client.service

import pl.putelita.ampervolt.avservices.client.model.Client
import java.util.*

interface ClientService {

    fun getAll(): MutableList<Client>

    fun getByUUid(uuid: UUID): Client

    fun create(client: Client): Client

    fun update(uuid: UUID, client: Client): Client

    fun deleteOrUpdate(uuid: UUID)
}