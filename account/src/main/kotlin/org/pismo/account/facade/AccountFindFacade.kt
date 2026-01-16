package org.pismo.account.facade

import org.pismo.account.context.AccountFindContext
import org.pismo.account.mapper.AccountMapper
import org.pismo.account.service.AccountService
import org.pismo.commons.dto.AccountResponse
import org.pismo.commons.facade.BaseFacade
import org.springframework.stereotype.Service

@Service
class AccountFindFacade(
    private val accountService: AccountService,
): BaseFacade() {

    fun findAccount(accountId: Long): AccountResponse {
        return AccountFindContext(accountId)
            .execAndLog(::findAccount)
            .execAndLog(::createResponse)
            .let { it.accountResponse!! }
    }

    private fun findAccount(context: AccountFindContext) =
        accountService.findById(context.accountId)
            .let(context::addAccount)

    private fun createResponse(context: AccountFindContext) =
        AccountMapper.toResponse(context.account!!)
            .let(context::addAccountResponse)
}