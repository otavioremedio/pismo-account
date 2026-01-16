package org.pismo.transaction.service

import org.pismo.commons.dto.AccountResponse
import org.pismo.commons.dto.UpdateAccountRequest
import org.pismo.transaction.exception.AccountNotFoundException
import org.pismo.transaction.integration.AccountClient
import org.springframework.stereotype.Service

@Service
class TransactionAccountService(private val accountClient: AccountClient) {

    fun getAccount(accountId: Long) =
        runCatching { accountClient.getAccount(accountId) }
            .getOrElse { throw AccountNotFoundException("Account $accountId not found") }

    fun updateAccount(account: AccountResponse) =
        UpdateAccountRequest(account.accountId, account.availableCreditLimit).apply {
            accountClient.updateAccount(this, this.id)
        }

}