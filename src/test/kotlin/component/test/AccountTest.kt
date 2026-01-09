package component.test

import component.config.BaseTest
import component.provider.AccountProvider
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import org.hamcrest.Matchers.*
import org.junit.jupiter.api.Test
import org.pismo.account.repository.AccountRepository
import org.springframework.beans.factory.annotation.Autowired

class AccountTest: BaseTest() {

    @Autowired
    lateinit var accountProvider: AccountProvider

    @Autowired
    lateinit var accountRepository: AccountRepository

    @Test
    fun `POST accounts - should create account and return 201`() {
        val payload = mapOf("document_number" to "98765432100")

        given()
            .body(payload)
        .`when`()
            .post("/accounts")
        .then()
            .statusCode(201)
            .body("account_id", notNullValue())
            .body("document_number", equalTo("98765432100"))

        val account = accountRepository.findByDocumentNumber("98765432100")
        assert(account.isPresent)
    }

    @Test
    fun `GET accounts by id - should return 200 when exists`() {
        val account = accountProvider.create("55566677788")

        given()
        .`when`()
            .get("/accounts/{id}", account.accountId)
        .then()
            .statusCode(200)
            .body("account_id", equalTo(account.accountId!!.toInt()))
            .body("document_number", equalTo(account.documentNumber))
    }

    @Test
    fun `GET accounts by id - should return 404 when not found`() {
        given()
            .contentType(ContentType.JSON)
        .`when`()
            .get("/accounts/{id}", 999999)
        .then()
            .statusCode(400)
            .body("status", equalTo(400))
            .body("title",  equalTo("invalid_parameter"))
            .body("detail", containsString("Account 999999 not found"))
    }
}