package org.pismo.transaction.mapper

import org.pismo.transaction.domain.Transaction
import org.pismo.transaction.dto.TransactionRequest
import org.pismo.transaction.dto.TransactionResponse
import org.pismo.transaction.enum.TransactionTypeEnum

object TransactionMapper {

    fun toEntity(request: TransactionRequest, operationType: org.pismo.transaction.domain.OperationType): Transaction {
        val type = TransactionTypeEnum.fromId(request.operationTypeId)

        return Transaction(
            accountId = request.accountId,
            operationTypeId = operationType,
            amount = when(type) {
                TransactionTypeEnum.DEBIT -> request.amount.abs().negate()
                else -> request.amount.abs()
            }
        )
    }

    fun toResponse(transaction: Transaction) = TransactionResponse(
            transactionId = transaction.transactionId!!,
            accountId = transaction.accountId,
            operationTypeId = transaction.operationTypeId.id,
            amount = transaction.amount.abs().setScale(2)
        )

}