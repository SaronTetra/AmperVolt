package pl.putelita.ampervolt.avservices.client.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pl.putelita.ampervolt.avservices.client.model.Client
import pl.putelita.ampervolt.avservices.client.repository.ClientRepository

@Service
class ClientServiceImpl @Autowired constructor (private val clientRepository: ClientRepository): ClientService  {

    override fun getAll(): MutableList<Client> {
        return clientRepository.findAll()
    }
}