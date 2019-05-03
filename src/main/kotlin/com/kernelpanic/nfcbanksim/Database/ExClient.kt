package com.kernelpanic.nfcbanksim.Database

import org.jetbrains.exposed.dao.IntIdTable

object ExClient: IntIdTable() {
    val name = ExClient.varchar("name", 30)
    val login = ExClient.varchar("login", 30)
    val created= ExClient.datetime("creationDate")
    val previousID = ExClient.integer("previousId")

}