package pl.putelita.ampervolt.avservices.client.api

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.putelita.ampervolt.avservices.client.model.Client
import pl.putelita.ampervolt.avservices.client.service.ClientService

@RestController
@RequestMapping("client")
class ClientController @Autowired constructor (private val clientService: ClientService) {

    @GetMapping
    fun getAll(): MutableList<Client> {
        return clientService.getAll()
    }
}