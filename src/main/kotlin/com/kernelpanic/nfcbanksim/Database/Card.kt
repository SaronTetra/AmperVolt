package com.kernelpanic.nfcbanksim.Database

import org.jetbrains.exposed.dao.IntIdTable

object Card : IntIdTable() {
    val number = integer("number")
    val date = datetime("date")
    val cvc = integer("cvc")
    val ownerID = integer("ownerID") // id of card owner
    val pin = integer("pin")

}