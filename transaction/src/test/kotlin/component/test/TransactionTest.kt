package component.test

import com.nhaarman.mockitokotlin2.KArgumentCaptor
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.verify
import component.config.BaseTest
import component.config.TransactionTestConfig
import component.wiremock.AccountStubs
import io.restassured.RestAssured.given
import java.math.BigDecimal
import org.assertj.core.api.Assertions.assertThat
import org.hamcrest.Matchers.containsString
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.notNullValue
import org.json.JSONObject
import org.junit.jupiter.api.Test
import org.pismo.commons.dto.AccountResponse
import org.pismo.transaction.repository.TransactionRepository
import org.pismo.transaction.service.TransactionAccountService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.mock.mockito.SpyBean
import org.springframework.test.context.ContextConfiguration

@ContextConfiguration(classes = [TransactionTestConfig::class])
class TransactionTest: BaseTest() {

    @Autowired
    private lateinit var transactionRepository: TransactionRepository

    @SpyBean
    private lateinit var accountService: TransactionAccountService

    @Test
    fun `POST transactions - should create transaction when input valid`() {

        val account = AccountStubs.stubAccountSuccess()
        AccountStubs.stubAccountUpdate()

        val payload = JSONObject()
            .put("account_id", account.accountId)
            .put("operation_type_id", 1)
            .put("amount", BigDecimal.valueOf(150.75))

        given()
            .body(payload.toString())
        .`when`()
            .post("/transactions")
        .then()
            .statusCode(201)
            .body("transaction_id", notNullValue())
            .body("account_id", equalTo(account.accountId.toInt()))
            .body("operation_type_id", equalTo(1))
            .body("amount", equalTo(BigDecimal.valueOf(150.75).toDouble().toFloat()))

        val all = transactionRepository.findAll()
        assert(all.isNotEmpty())
    }

    @Test
    fun `POST transactions - should create transaction when is debit`() {

        val account = AccountStubs.stubAccountSuccess()
        AccountStubs.stubAccountUpdate()
        val requestCaptor: KArgumentCaptor<AccountResponse> = argumentCaptor()

        val payload = JSONObject()
            .put("account_id", account.accountId)
            .put("operation_type_id", 1)
            .put("amount", BigDecimal.valueOf(200.00))

        given()
            .body(payload.toString())
            .`when`()
            .post("/transactions")
            .then()
            .statusCode(201)
            .body("transaction_id", notNullValue())
            .body("account_id", equalTo(account.accountId.toInt()))
            .body("operation_type_id", equalTo(1))
            .body("amount", equalTo(BigDecimal.valueOf(200.0).toDouble().toFloat()))

        val all = transactionRepository.findAll()
        assert(all.isNotEmpty())

        verify(accountService).updateAccount(requestCaptor.capture())
        assertThat(requestCaptor.firstValue.availableCreditLimit).isEqualTo(BigDecimal.valueOf(4800.00))
    }

    @Test
    fun `POST transactions - should create transaction when is credit`() {

        val account = AccountStubs.stubAccountSuccess()
        AccountStubs.stubAccountUpdate()
        val requestCaptor: KArgumentCaptor<AccountResponse> = argumentCaptor()

        val payload = JSONObject()
            .put("account_id", account.accountId)
            .put("operation_type_id", 4)
            .put("amount", BigDecimal.valueOf(200.00))

        given()
            .body(payload.toString())
            .`when`()
            .post("/transactions")
            .then()
            .statusCode(201)
            .body("transaction_id", notNullValue())
            .body("account_id", equalTo(account.accountId.toInt()))
            .body("operation_type_id", equalTo(4))
            .body("amount", equalTo(BigDecimal.valueOf(200.0).toDouble().toFloat()))

        val all = transactionRepository.findAll()
        assert(all.isNotEmpty())

        verify(accountService).updateAccount(requestCaptor.capture())
        assertThat(requestCaptor.firstValue.availableCreditLimit).isEqualTo(BigDecimal.valueOf(5200.00))
    }

    @Test
    fun `POST transactions - should return error when operation type invalid`() {
        val account = AccountStubs.stubAccountSuccess()
        AccountStubs.stubAccountUpdate()

        val payload = JSONObject()
            .put("account_id", account.accountId)
            .put("operation_type_id", 99)
            .put("amount", BigDecimal.valueOf(100.00))

        given()
            .body(payload.toString())
        .`when`()
            .post("/transactions")
        .then()
            .statusCode(400)
            .body("status", equalTo(400))
            .body("title",  equalTo("invalid_parameter"))
            .body("detail", containsString("Operation Type 99 is invalid"))
    }
}