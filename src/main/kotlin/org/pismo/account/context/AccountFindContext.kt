package org.pismo.account.context

import org.pismo.account.domain.Account
import org.pismo.account.dto.AccountResponse

data class AccountFindContext(
    val accountId: Long,
    override val account: Account? = null,
    override val accountResponse: AccountResponse? = null,
): SingleAccountContext<AccountFindContext> {
    override fun addAccount(account: Account) = copy(account = account)
    override fun addAccountResponse(accountResponse: AccountResponse) = copy(accountResponse = accountResponse)
}
