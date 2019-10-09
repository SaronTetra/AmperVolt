package pl.putelita.ampervolt.avservices.client.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pl.putelita.ampervolt.avservices.client.exception.ClientLoginExistsException
import pl.putelita.ampervolt.avservices.client.exception.ClientNotFoundException
import pl.putelita.ampervolt.avservices.client.model.Client
import pl.putelita.ampervolt.avservices.client.repository.ClientRepository
import pl.putelita.ampervolt.avservices.client.validator.ClientValidator
import java.time.LocalDateTime
import java.util.*

@Service
class ClientServiceImpl @Autowired constructor (private val clientRepository: ClientRepository): ClientService  {

    override fun getAll(): MutableList<Client> {
        return clientRepository.findAllByRemovedIsFalse()
    }

    override fun getByUUid(uuid: UUID): Client {
        return clientRepository.findByUuid(uuid) ?: throw ClientNotFoundException()
    }

    override fun create(client: Client): Client {
        if (clientRepository.findByLoginIgnoreCase(client.login) != null) {
            throw ClientLoginExistsException()
        }
        return clientRepository.save(client)
    }

    override fun update(uuid: UUID, client: Client): Client {
        var clientDB: Client = clientRepository.findByUuid(uuid) ?: throw ClientNotFoundException()
        if (!clientDB.login.equals(client.login)) {
            ClientValidator.validateLogin(client, clientRepository.findByLoginIgnoreCase(client.login))
        }
        setterClient(clientDB, client)
        return clientRepository.save(clientDB)
    }

    fun setterClient(clientDB: Client, client: Client){
        clientDB.name = client.name
        clientDB.secondName = client.secondName
        clientDB.surname = client.surname
        clientDB.login = client.login
        clientDB.password = client.password
    }

    override fun deleteOrUpdate(uuid: UUID) {
        var clientDB: Client =  clientRepository.findByUuid(uuid) ?: throw ClientNotFoundException()
        if (clientDB.removed == false || clientDB.removed == null) {
            clientDB.removed = true
            clientDB.removeDate = LocalDateTime.now()
        }
        clientRepository.save(clientDB)
    }
}