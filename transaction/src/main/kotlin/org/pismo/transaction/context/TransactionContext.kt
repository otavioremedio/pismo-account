package org.pismo.transaction.context

import org.pismo.commons.dto.AccountResponse
import org.pismo.transaction.domain.Transaction
import org.pismo.transaction.dto.TransactionResponse

interface TransactionContext

interface SingleTransactionContext<out T : SingleTransactionContext<T>> : TransactionContext {
    val account: AccountResponse?
    val operationType: org.pismo.transaction.domain.OperationType?
    val transaction: Transaction?
    val transactionResponse: TransactionResponse?
    fun addAccount(account: AccountResponse): T
    fun addOperationType(operationType: org.pismo.transaction.domain.OperationType): T
    fun addTransaction(transaction: Transaction): T
    fun addTransactionResponse(transactionResponse: TransactionResponse): T
}