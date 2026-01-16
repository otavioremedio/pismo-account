package org.pismo.transaction.service

import java.math.BigDecimal
import org.pismo.commons.dto.AccountResponse
import org.pismo.transaction.domain.OperationType
import org.pismo.transaction.domain.Transaction
import org.pismo.transaction.enum.TransactionTypeEnum
import org.pismo.transaction.exception.AvailableLimitException
import org.pismo.transaction.repository.TransactionRepository
import org.springframework.stereotype.Service

@Service
class TransactionService(private val transactionRepository: TransactionRepository) {

    fun save(transaction: Transaction): Transaction = transactionRepository.save(transaction)

    fun validateLimit(amount: BigDecimal, availableCreditLimit: BigDecimal, operationType: OperationType) {
        val transactionType = TransactionTypeEnum.fromId(operationType.id)
        if(transactionType == TransactionTypeEnum.DEBIT && amount > availableCreditLimit) throw AvailableLimitException("The amount is greater than available limit")
    }

    fun updateAvailableLimit(account: AccountResponse, transaction: Transaction): AccountResponse {
        val operationType = TransactionTypeEnum.fromId(transaction.operationTypeId.id)
        val newLimit =
            if(operationType == TransactionTypeEnum.DEBIT) account.availableCreditLimit - transaction.amount.abs()
            else account.availableCreditLimit + transaction.amount.abs()

        return account.copy(availableCreditLimit = newLimit)
    }
}