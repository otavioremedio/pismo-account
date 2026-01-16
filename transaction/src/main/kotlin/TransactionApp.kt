package org.pismo.transaction

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication(scanBasePackages = ["org.pismo"])
@EnableFeignClients(basePackages = ["org.pismo.transaction.integration"])
class TransactionApp

fun main(args: Array<String>) {
    runApplication<TransactionApp>(*args)
}
