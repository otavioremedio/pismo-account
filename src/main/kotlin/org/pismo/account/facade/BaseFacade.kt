package org.pismo.account.facade

import kotlin.jvm.internal.CallableReference
import org.pismo.account.config.MapperLogConfiguration
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.dao.DataIntegrityViolationException

open class BaseFacade {

    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)
    private val logMapper = MapperLogConfiguration.logMapper

    protected fun <T> T.execAndLog(func: (T) -> T): T {
        val method = (func as CallableReference).name

        return try {
            func(this).also { r -> logger.info("Method: {}, Param: {}", method, logMapper.writeValueAsString(r)) }
        } catch (ex: Throwable) {
            val message = if(ex is DataIntegrityViolationException) ex.message?.split("VALUES")?.first() else ex.message
            logger.error("Method: {}, Exception: {}, Context: {}", method, message, logMapper.writeValueAsString(this!!))
            throw ex
        }
    }
}

