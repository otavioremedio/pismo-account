package org.pismo.account.org.pismo.account.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class AccountRequest(
    @field:JsonProperty("document_number")
    val documentNumber: String
)