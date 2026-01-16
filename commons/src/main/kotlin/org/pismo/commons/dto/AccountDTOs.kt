package org.pismo.commons.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

data class AccountResponse(
    @field:JsonProperty("account_id")
    val accountId: Long,
    @field:JsonProperty("document_number")
    val documentNumber: String,
    @field:JsonProperty("available_credit_limit")
    val availableCreditLimit: BigDecimal
)


data class UpdateAccountRequest(
    val id: Long,
    val availableCreditLimit: BigDecimal
)