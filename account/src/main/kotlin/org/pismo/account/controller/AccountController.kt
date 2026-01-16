package org.pismo.account.controller

import jakarta.validation.Valid
import org.pismo.account.facade.AccountCreateFacade
import org.pismo.account.facade.AccountFindFacade
import org.pismo.account.org.pismo.account.dto.AccountRequest
import org.pismo.account.org.pismo.account.facade.AccountUpdateFacade
import org.pismo.commons.dto.AccountResponse
import org.pismo.commons.dto.UpdateAccountRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/accounts")
class AccountController(
    private val accountCreateFacade: AccountCreateFacade,
    private val accountFindFacade: AccountFindFacade,
    private val accountUpdateFacade: AccountUpdateFacade
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createAccount(@Valid @RequestBody request: AccountRequest): AccountResponse = accountCreateFacade.createAccount(request)

    @GetMapping("/{accountId}")
    @ResponseStatus(HttpStatus.OK)
    fun getAccount(@PathVariable accountId: Long): AccountResponse = accountFindFacade.findAccount(accountId)

    @PutMapping("/{accountId}")
    @ResponseStatus(HttpStatus.OK)
    fun updateAccount(@Valid @RequestBody request: UpdateAccountRequest) = accountUpdateFacade.updateAccount(request)
}