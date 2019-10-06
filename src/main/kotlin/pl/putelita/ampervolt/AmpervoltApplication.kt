package pl.putelita.ampervolt

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AmpervoltApplication

fun main(args: Array<String>) {
    runApplication<AmpervoltApplication>(*args)
}
