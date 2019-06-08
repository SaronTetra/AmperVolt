package com.kernelpanic.nfcbanksim.Database

import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntIdTable

//class Client(id: EntityID<Int>): Entity<Int>(id){
//    companion object : EntityClass<Int, Client>(Clients)
//
//    var name by Clients.name
//    var login by Clients.login
//    var password by Clients.password
//    var balance by Clients.balance
//    var creationDate by Clients.creationDate
//
//
//
//}


object Client: IntIdTable() {
    val login = varchar("login", 32)
    val password = varchar("password", 256)
    val creationDate = datetime("creationDate")
}