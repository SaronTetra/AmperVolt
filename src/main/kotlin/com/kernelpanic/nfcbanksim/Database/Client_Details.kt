package com.kernelpanic.nfcbanksim.Database

import org.jetbrains.exposed.dao.IntIdTable

object Client_Details: IntIdTable() {

    val name = varchar("name", 32)
    val surname = varchar("surname", 32)
    val avatar = blob("avatar")
}