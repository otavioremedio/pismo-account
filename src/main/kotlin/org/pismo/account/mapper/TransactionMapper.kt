package org.pismo.account.mapper

import org.pismo.account.domain.OperationType
import org.pismo.account.domain.Transaction
import org.pismo.account.dto.TransactionRequest
import org.pismo.account.dto.TransactionResponse
import org.pismo.account.enum.TransactionTypeEnum

object TransactionMapper {

    fun toEntity(request: TransactionRequest, operationType: OperationType): Transaction {
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


    fun toResponse(transaction: Transaction): TransactionResponse {
        val type = TransactionTypeEnum.fromId(transaction.operationTypeId.id)

        return TransactionResponse(
            transactionId = transaction.transactionId!!,
            accountId = transaction.accountId,
            operationTypeId = transaction.operationTypeId.id,
            amount = when(type) {
                TransactionTypeEnum.DEBIT -> transaction.amount.abs()
                else -> transaction.amount.abs()
            }
        )
    }
}