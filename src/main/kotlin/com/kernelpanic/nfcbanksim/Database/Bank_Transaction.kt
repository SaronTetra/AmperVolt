package com.kernelpanic.nfcbanksim.Database

import org.jetbrains.exposed.dao.IntIdTable

object Bank_Transaction: IntIdTable() {
    val from_account = varchar("from_account", 26) //id of clinet who is ordering a transfer
    val to_account = varchar("to_account", 26) //id of clinet who is ordering a transfer
    val order_date = datetime("order_date")
    val money = double("money")
    val type = integer("type_id") //type of transaction
    val title = varchar("title", 200)

}