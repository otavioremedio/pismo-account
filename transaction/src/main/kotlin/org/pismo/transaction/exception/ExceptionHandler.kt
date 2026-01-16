package org.pismo.transaction.exception

import jakarta.persistence.EntityNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail.forStatusAndDetail
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler

class AccountNotFoundException(message: String): EntityNotFoundException(message)
class AvailableLimitException(message: String): EntityNotFoundException(message)

@ExceptionHandler(AvailableLimitException::class)
fun handleAvailableLimitException(ex: AvailableLimitException): ResponseEntity<Any> {
    val problemDetail = forStatusAndDetail(
        HttpStatus.UNPROCESSABLE_ENTITY,
        ex.message ?: ex.localizedMessage
    )
    problemDetail.title = "available_limit_exceed"
    return ResponseEntity.of(problemDetail).build()
}