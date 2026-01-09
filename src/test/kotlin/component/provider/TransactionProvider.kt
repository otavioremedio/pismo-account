package component.provider

import org.pismo.account.domain.OperationType
import org.pismo.account.domain.Transaction
import org.pismo.account.repository.OperationTypeRepository
import org.pismo.account.repository.TransactionRepository
import org.springframework.boot.test.context.TestComponent
import java.math.BigDecimal

@TestComponent
class TransactionProvider(
    private val transactionRepository: TransactionRepository,
    private val operationTypeRepository: OperationTypeRepository
) {
    fun createForAccount(accountId: Long, operationTypeId: Long = 1L, amount: Double = 100.50): Transaction {
        val opType: OperationType = operationTypeRepository.findById(operationTypeId).orElseThrow()
        val transaction = Transaction(
            accountId = accountId,
            operationTypeId = opType,
            amount = BigDecimal.valueOf(amount)
        )
        return transactionRepository.save(transaction)
    }
}