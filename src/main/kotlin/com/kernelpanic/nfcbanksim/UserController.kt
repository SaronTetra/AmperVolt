package com.kernelpanic.nfcbanksim

import com.fasterxml.jackson.databind.ser.std.StdJdkSerializers
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicLong

@RestController
class UserController {

    val counter = AtomicLong()

    @GetMapping("/user")
    fun hello(@RequestParam(value = "name", defaultValue = "World") name : String) =
            User(counter.incrementAndGet(), "Hello, $name")
}