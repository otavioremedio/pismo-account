package org.pismo.account.context

import org.pismo.account.domain.Account
import org.pismo.account.domain.OperationType
import org.pismo.account.domain.Transaction
import org.pismo.account.dto.TransactionRequest
import org.pismo.account.dto.TransactionResponse

data class TransactionCreateContext(
    val request: TransactionRequest,
    override val account: Account? = null,
    override val operationType: OperationType? = null,
    override val transaction: Transaction? = null,
    override val transactionResponse: TransactionResponse? = null
): SingleTransactionContext<TransactionCreateContext> {
    override fun addAccount(account: Account) = copy(account = account)
    override fun addOperationType(operationType: OperationType) = copy(operationType = operationType)
    override fun addTransaction(transaction: Transaction) = copy(transaction = transaction)
    override fun addTransactionResponse(transactionResponse: TransactionResponse) = copy(transactionResponse = transactionResponse)
}