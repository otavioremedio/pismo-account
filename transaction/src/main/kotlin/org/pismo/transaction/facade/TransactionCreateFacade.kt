package org.pismo.transaction.facade

import org.pismo.commons.facade.BaseFacade
import org.pismo.transaction.context.TransactionCreateContext
import org.pismo.transaction.dto.TransactionRequest
import org.pismo.transaction.dto.TransactionResponse
import org.pismo.transaction.mapper.TransactionMapper
import org.pismo.transaction.service.OperationService
import org.pismo.transaction.service.TransactionAccountService
import org.pismo.transaction.service.TransactionService
import org.springframework.stereotype.Service

@Service
class TransactionCreateFacade(
    private val operationService: OperationService,
    private val transactionAccountService: TransactionAccountService,
    private val transactionService: TransactionService,
): BaseFacade() {
    fun createTransaction(request: TransactionRequest): TransactionResponse {
        return TransactionCreateContext(request)
            .execAndLog(::findAccount)
            .execAndLog(::findOperationType)
            .execAndLog(::validateLimit)
            .execAndLog(::createTransaction)
            .execAndLog(::updateAvailableLimit)
            .execAndLog(::saveTransaction)
            .execAndLog(::updateAccount)
            .execAndLog(::createResponse)
            .let { it.transactionResponse!! }
    }

    private fun findAccount(context: TransactionCreateContext) =
        transactionAccountService.getAccount(context.request.accountId)
            .let(context::addAccount)

    private fun findOperationType(context: TransactionCreateContext) =
        operationService.findById(context.request.operationTypeId)
            .let(context::addOperationType)

    private fun validateLimit(context: TransactionCreateContext) =
        transactionService.validateLimit(context.request.amount, context.account!!.availableCreditLimit, context.operationType!!)
            .let{ context }

    private fun createTransaction(context: TransactionCreateContext) =
        TransactionMapper.toEntity(context.request, context.operationType!!)
            .let(context::addTransaction)

    private fun updateAvailableLimit(context: TransactionCreateContext) =
        transactionService.updateAvailableLimit(context.account!!, context.transaction!!)
            .let(context::addAccount)

    private fun saveTransaction(context: TransactionCreateContext) =
        transactionService.save(context.transaction!!)
            .let(context::addTransaction)

    private fun updateAccount(context: TransactionCreateContext) =
        transactionAccountService.updateAccount(context.account!!)
            .let{ context }

    private fun createResponse(context: TransactionCreateContext) =
        TransactionMapper.toResponse(context.transaction!!)
            .let(context::addTransactionResponse)
}