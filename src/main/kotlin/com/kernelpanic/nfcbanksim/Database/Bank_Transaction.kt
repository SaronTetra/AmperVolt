package com.kernelpanic.nfcbanksim.Database

import org.jetbrains.exposed.dao.IntIdTable

object Bank_Transaction: IntIdTable() {
    val fromId = integer("fromId") //id of clinet who is ordering a transfer
    val toId = integer("toId") //id of clinet who is recieving a transfer
    val orderDate = datetime("orderDate")
    val executionDate = datetime("executionDate")
    val money = double("money")
    val type = varchar("type", 3) //type of transaction
    val title = varchar("title", 100)

}