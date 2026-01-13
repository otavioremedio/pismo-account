package org.pismo.account.config

import jakarta.annotation.PostConstruct
import org.pismo.account.domain.Account
import org.pismo.account.dto.AccountRequest
import org.pismo.account.dto.AccountResponse
import org.pismo.account.encode.AccountLogMixin
import org.pismo.commons.facade.LogMapperRegistry
import org.springframework.context.annotation.Configuration

@Configuration
class AccountLogConfiguration {
    @PostConstruct
    fun setupLogs() {
        LogMapperRegistry.registerMixIn(Account::class.java, AccountLogMixin::class.java)
        LogMapperRegistry.registerMixIn(AccountRequest::class.java, AccountLogMixin::class.java)
        LogMapperRegistry.registerMixIn(AccountResponse::class.java, AccountLogMixin::class.java)
    }
}