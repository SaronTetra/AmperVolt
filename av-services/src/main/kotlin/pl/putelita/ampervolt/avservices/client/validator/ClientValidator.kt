package pl.putelita.ampervolt.avservices.client.validator

import pl.putelita.ampervolt.avservices.client.exception.ClientLoginExistsException
import pl.putelita.ampervolt.avservices.client.model.Client

object ClientValidator {

    fun validateLogin(clientDB: Client, client: Client?) {
        if (client?.login.equals(clientDB.login)) {
            throw ClientLoginExistsException()
        }
    }
}