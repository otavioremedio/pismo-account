package org.pismo.transaction.integration

import org.pismo.commons.dto.AccountResponse
import org.pismo.commons.dto.UpdateAccountRequest
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(name = "account-client", url = "\${services.account.internal-url}")
interface AccountClient {

    @GetMapping("/accounts/{accountId}")
    fun getAccount(@PathVariable accountId: Long): AccountResponse

    @PutMapping("/accounts/{accountId}")
    fun updateAccount(@RequestBody updateAccountRequest: UpdateAccountRequest, @PathVariable accountId: Long)
}
