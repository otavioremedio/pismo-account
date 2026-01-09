package org.pismo.account.context

import org.pismo.account.domain.Account
import org.pismo.account.domain.OperationType
import org.pismo.account.domain.Transaction
import org.pismo.account.dto.TransactionResponse

interface TransactionContext

interface SingleTransactionContext<out T : SingleTransactionContext<T>> : TransactionContext {
    val account: Account?
    val operationType: OperationType?
    val transaction: Transaction?
    val transactionResponse: TransactionResponse?
    fun addAccount(account: Account): T
    fun addOperationType(operationType: OperationType): T
    fun addTransaction(transaction: Transaction): T
    fun addTransactionResponse(transactionResponse: TransactionResponse): T
}