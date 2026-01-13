package org.pismo.commons.config

import jakarta.annotation.PostConstruct
import org.pismo.commons.dto.AccountResponse
import org.pismo.commons.encode.AccountLogMixin
import org.pismo.commons.facade.LogMapperRegistry
import org.springframework.context.annotation.Configuration

@Configuration
class CommonsLogConfiguration {
    @PostConstruct
    fun setupLogs() {
        LogMapperRegistry.registerMixIn(AccountResponse::class.java, AccountLogMixin::class.java)
    }
}