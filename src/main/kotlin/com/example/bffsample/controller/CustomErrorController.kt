package com.example.bffsample.controller

import com.example.bffsample.model.forfrontend.ApplicationError
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.servlet.error.ErrorController
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.RequestDispatcher
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/error")
class CustomErrorController : ErrorController {

    private val log = LogFactory.getLog(CustomErrorController::class.java)

    @Value("\${server.error.path:\${error.path:/error}}")
    private val errorPath: String = ""

    override fun getErrorPath(): String {
        return errorPath
    }

    @RequestMapping
    fun handleError(request: HttpServletRequest): ResponseEntity<ApplicationError> {

        val statusCode = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE)
        if (statusCode == HttpStatus.NOT_FOUND.value()) {
            val error = ApplicationError("E1002", "Request url is incorrect.")
            return ResponseEntity<ApplicationError>(error, HttpStatus.NOT_FOUND)
        }

        log.error(request.getAttribute(RequestDispatcher.ERROR_EXCEPTION))
        val error = ApplicationError("E1003", "Unknown error was happened.")
        return ResponseEntity<ApplicationError>(error, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}