package component.config

import component.provider.AccountProvider
import org.pismo.account.repository.AccountRepository
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean

@TestConfiguration
class AccountTestConfig {
    @Bean
    fun accountProvider(accountRepository: AccountRepository) =
        AccountProvider(accountRepository)
}
