package com.kernelpanic.nfcbanksim.Database

import org.jetbrains.exposed.dao.IntIdTable

object Card : IntIdTable() {
    val number = varchar("number", 32)
    val creationDate = datetime("creationDate")
    val expirationDate = datetime("expirationDate")
    val cvc = integer("cvc")
    val ownerAccountID = integer("ownerAccountID") // id of card owner
    val pin = integer("pin")
    val uuid = varchar("nfc_uuid", 36)

}