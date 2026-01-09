package org.pismo.account.service

import jakarta.persistence.EntityNotFoundException
import org.pismo.account.domain.Account
import org.pismo.account.repository.AccountRepository
import org.springframework.stereotype.Service

@Service
class AccountService(private val accountRepository: AccountRepository) {

    fun findById(id: Long): Account = accountRepository.findById(id)
        .orElseThrow { EntityNotFoundException("Account $id not found") }

    fun save(account: Account): Account = accountRepository.save(account)
}