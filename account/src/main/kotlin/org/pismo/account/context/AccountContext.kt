package org.pismo.account.context

import org.pismo.account.domain.Account
import org.pismo.commons.dto.AccountResponse

interface AccountContext

interface SingleAccountContext<out T : SingleAccountContext<T>> : AccountContext {
    val account: Account?
    val accountResponse: AccountResponse?
    fun addAccount(account: Account): T
    fun addAccountResponse(accountResponse: AccountResponse): T
}