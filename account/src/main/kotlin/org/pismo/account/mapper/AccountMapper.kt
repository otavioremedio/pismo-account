package org.pismo.account.mapper

import org.pismo.account.domain.Account
import org.pismo.account.org.pismo.account.dto.AccountRequest
import org.pismo.commons.dto.AccountResponse
import org.pismo.commons.dto.UpdateAccountRequest

object AccountMapper {

    fun toEntity(request: AccountRequest) = Account(documentNumber = request.documentNumber)
    fun toResponse(account: Account) = AccountResponse(account.accountId!!, account.documentNumber, account.availableCreditLimit)
    fun toEntity(request: UpdateAccountRequest, account: Account) = account.copy(availableCreditLimit = request.availableCreditLimit)
}