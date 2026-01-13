package org.pismo.transaction.facade

import org.pismo.account.service.AccountService
import org.pismo.commons.facade.BaseFacade
import org.pismo.transaction.context.TransactionCreateContext
import org.pismo.transaction.dto.TransactionRequest
import org.pismo.transaction.dto.TransactionResponse
import org.pismo.transaction.mapper.TransactionMapper
import org.pismo.transaction.service.OperationService
import org.pismo.transaction.service.TransactionService
import org.springframework.stereotype.Service

@Service
class TransactionCreateFacade(
    private val operationService: OperationService,
    private val accountService: AccountService,
    private val transactionService: TransactionService,
): BaseFacade() {
    fun createTransaction(request: TransactionRequest): TransactionResponse {
        return TransactionCreateContext(request)
            .execAndLog(::findAccount)
            .execAndLog(::findOperationType)
            .execAndLog(::createTransaction)
            .execAndLog(::saveTransaction)
            .execAndLog(::createResponse)
            .let { it.transactionResponse!! }
    }

    private fun findAccount(context: TransactionCreateContext) =
        accountService.findById(context.request.accountId)
            .let(context::addAccount)

    private fun findOperationType(context: TransactionCreateContext) =
        operationService.findById(context.request.operationTypeId)
            .let(context::addOperationType)

    private fun createTransaction(context: TransactionCreateContext) =
        TransactionMapper.toEntity(context.request, context.operationType!!)
            .let(context::addTransaction)

    private fun saveTransaction(context: TransactionCreateContext) =
        transactionService.save(context.transaction!!)
            .let(context::addTransaction)

    private fun createResponse(context: TransactionCreateContext) =
        TransactionMapper.toResponse(context.transaction!!)
            .let(context::addTransactionResponse)
}