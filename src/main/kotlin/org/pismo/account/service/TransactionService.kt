package org.pismo.account.service

import org.pismo.account.domain.Transaction
import org.pismo.account.repository.TransactionRepository
import org.springframework.stereotype.Service

@Service
class TransactionService(private val transactionRepository: TransactionRepository) {

    fun save(transaction: Transaction): Transaction = transactionRepository.save(transaction)

}