package pl.putelita.ampervolt.avservices.account.api

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import pl.putelita.ampervolt.avservices.account.model.Account
import pl.putelita.ampervolt.avservices.account.service.AccountService
import java.util.*

@RestController
@RequestMapping("account")
class AccountController @Autowired constructor(private val accountService: AccountService) {

    @GetMapping
    fun getAll(): MutableList<Account> {
        return accountService.getAll()
    }

    @GetMapping("{uuid}")
    fun getByUuid(@PathVariable uuid: UUID): Account {
        return accountService.getByUuid(uuid)
    }

    @PostMapping
    fun create(@RequestBody account: Account): Account {
        return accountService.create(account)
    }

    @DeleteMapping("{uuid}")
    fun deleteOrUpdate(@PathVariable uuid: UUID) {
        return accountService.deleteOrUpdate(uuid)
    }

}