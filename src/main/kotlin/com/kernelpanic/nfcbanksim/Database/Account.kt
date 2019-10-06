package com.kernelpanic.nfcbanksim.Database

import org.jetbrains.exposed.dao.IntIdTable

object Account: IntIdTable() {
    val number = varchar("number", 26)
    val balance = double("balance")
    val owner_id = integer("owner_id")
}