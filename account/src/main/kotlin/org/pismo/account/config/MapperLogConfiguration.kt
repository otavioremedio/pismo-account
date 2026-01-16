package org.pismo.account.config

import jakarta.annotation.PostConstruct
import org.pismo.account.domain.Account
import org.pismo.account.org.pismo.account.dto.AccountRequest
import org.pismo.commons.dto.AccountResponse
import org.pismo.commons.encode.AccountLogMixin
import org.pismo.commons.facade.LogMapperRegistry
import org.springframework.context.annotation.Configuration

@Configuration
class AccountLogConfiguration {
    @PostConstruct
    fun setupLogs() {
        LogMapperRegistry.registerMixIn(Account::class.java, AccountLogMixin::class.java)
        LogMapperRegistry.registerMixIn(AccountRequest::class.java, AccountLogMixin::class.java)
    }
}