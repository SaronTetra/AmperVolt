package com.kernelpanic.nfcbanksim.Database

import org.jetbrains.exposed.dao.IntIdTable

object Client: IntIdTable() {
    val name = varchar("name", 30)
    val login = varchar("login", 30)
    val password = varchar("password", 30)
    val balance = double("balance")
    val created= datetime("creationDate")
}