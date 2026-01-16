package org.pismo.account.org.pismo.account.facade

import org.pismo.account.mapper.AccountMapper
import org.pismo.account.org.pismo.account.context.AccountUpdateContext
import org.pismo.account.service.AccountService
import org.pismo.commons.dto.UpdateAccountRequest
import org.pismo.commons.facade.BaseFacade
import org.springframework.stereotype.Service

@Service
class AccountUpdateFacade(
    private val accountService: AccountService,
): BaseFacade() {

    fun updateAccount(request: UpdateAccountRequest) =
        AccountUpdateContext(request)
            .execAndLog(::findAccount)
            .execAndLog(::update)
            .execAndLog(::saveAccount)

    private fun findAccount(context: AccountUpdateContext) =
        accountService.findById(context.request.id)
            .let(context::addAccount)

    private fun update(context: AccountUpdateContext) =
        AccountMapper.toEntity(context.request, context.account!!)
            .let(context::addAccount)

    private fun saveAccount(context: AccountUpdateContext) =
        accountService.save(context.account!!)
            .let(context::addAccount)

}