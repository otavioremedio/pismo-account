package component.provider

import org.pismo.account.domain.Account
import org.pismo.account.repository.AccountRepository
import org.springframework.boot.test.context.TestComponent

@TestComponent
class AccountProvider(private val accountRepository: AccountRepository){

    fun create(documentNumber: String = "12345678901"): Account {
        val account = Account(
            documentNumber = documentNumber
        )
        return accountRepository.save(account)
    }
}