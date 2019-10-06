package com.kernelpanic.nfcbanksim.Database

import org.jetbrains.exposed.dao.IntIdTable

object Type: IntIdTable() {

    val abbr = varchar("abbr", 3)
    val name = varchar("name", 32)

}