package org.pismo.account.org.pismo.account.context

import org.pismo.account.context.SingleAccountContext
import org.pismo.account.domain.Account
import org.pismo.commons.dto.AccountResponse
import org.pismo.commons.dto.UpdateAccountRequest

data class AccountUpdateContext(
    val request: UpdateAccountRequest,
    override val account: Account? = null,
    override val accountResponse: AccountResponse? = null,
): SingleAccountContext<AccountUpdateContext> {
    override fun addAccount(account: Account) = copy(account = account)
    override fun addAccountResponse(accountResponse: AccountResponse) = copy(accountResponse = accountResponse)
}
