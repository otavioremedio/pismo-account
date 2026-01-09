package component.config

import component.provider.AccountProvider
import component.provider.TransactionProvider
import org.pismo.account.repository.AccountRepository
import org.pismo.account.repository.OperationTypeRepository
import org.pismo.account.repository.TransactionRepository
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean

@TestConfiguration
class TestConfig {
    @Bean
    fun accountProvider(accountRepository: AccountRepository) =
        AccountProvider(accountRepository)

    @Bean
    fun transactionProvider(transactionRepository: TransactionRepository,
                            operationTypeRepository: OperationTypeRepository) =
        TransactionProvider(transactionRepository, operationTypeRepository)
}
