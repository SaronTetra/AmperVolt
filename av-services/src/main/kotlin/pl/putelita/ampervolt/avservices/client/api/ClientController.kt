package pl.putelita.ampervolt.avservices.client.api

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import pl.putelita.ampervolt.avservices.client.model.Client
import pl.putelita.ampervolt.avservices.client.service.ClientService
import java.util.*

@RestController
@RequestMapping("client")
class ClientController @Autowired constructor (private val clientService: ClientService) {

    @GetMapping
    fun getAll(): MutableList<Client> {
        return clientService.getAll()
    }

    @GetMapping("{uuid}")
    fun getByUuid(@PathVariable uuid: UUID): Client {
        return clientService.getByUUid(uuid)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody client: Client): Client {
        return clientService.create(client)
    }

    @PutMapping("{uuid}")
    fun update(@RequestBody client: Client, @PathVariable uuid: UUID): Client {
        return clientService.update(uuid, client);
    }

    @DeleteMapping("{uuid}")
    fun deleteOrUpdate(@PathVariable uuid: UUID) {
        clientService.deleteOrUpdate(uuid)
    }
}