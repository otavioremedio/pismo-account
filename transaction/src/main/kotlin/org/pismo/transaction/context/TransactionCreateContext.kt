package org.pismo.transaction.context

import org.pismo.commons.dto.AccountResponse
import org.pismo.transaction.domain.Transaction
import org.pismo.transaction.dto.TransactionRequest
import org.pismo.transaction.dto.TransactionResponse

data class TransactionCreateContext(
    val request: TransactionRequest,
    override val account: AccountResponse? = null,
    override val operationType: org.pismo.transaction.domain.OperationType? = null,
    override val transaction: Transaction? = null,
    override val transactionResponse: TransactionResponse? = null
): SingleTransactionContext<TransactionCreateContext> {
    override fun addAccount(account: AccountResponse) = copy(account = account)
    override fun addOperationType(operationType: org.pismo.transaction.domain.OperationType) = copy(operationType = operationType)
    override fun addTransaction(transaction: Transaction) = copy(transaction = transaction)
    override fun addTransactionResponse(transactionResponse: TransactionResponse) = copy(transactionResponse = transactionResponse)
}