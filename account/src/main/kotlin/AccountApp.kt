package org.pismo.account

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["org.pismo"])
class AccountApp

fun main(args: Array<String>) {
    runApplication<AccountApp>(*args)
}
