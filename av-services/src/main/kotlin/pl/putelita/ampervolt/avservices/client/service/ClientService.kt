package pl.putelita.ampervolt.avservices.client.service

import pl.putelita.ampervolt.avservices.client.model.Client

interface ClientService {

    fun getAll(): MutableList<Client>
}