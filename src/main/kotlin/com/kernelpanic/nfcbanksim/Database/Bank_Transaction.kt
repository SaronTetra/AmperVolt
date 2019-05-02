package com.kernelpanic.nfcbanksim.Database

import org.jetbrains.exposed.dao.IntIdTable

object Bank_Transaction: IntIdTable() {
    val fromId = integer("fromId") //id of clinet who is ordering a transfer
    val toId = integer("toId") //id of clinet who is recieving a transfer
    val orderDate = datetime("orderDate")
    val executionDate = datetime("executionDate")
    val money = Client.double("money")
    val type = Client.varchar("type", 30) //type of transaction

}