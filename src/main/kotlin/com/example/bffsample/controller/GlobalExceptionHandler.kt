package com.example.bffsample.controller

import com.example.bffsample.model.forfrontend.ApplicationError
import org.apache.commons.logging.LogFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.client.ResourceAccessException
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import javax.servlet.http.HttpServletRequest


@RestControllerAdvice
class GlobalExceptionHandler : ResponseEntityExceptionHandler() {
    private val log = LogFactory.getLog(GlobalExceptionHandler::class.java)

    @ExceptionHandler(ResourceAccessException::class)
    @ResponseBody
    fun handleResourceAccessException(request: HttpServletRequest, ex: ResourceAccessException): ResponseEntity<ApplicationError> {
        log.error(ex.message)

        val error = ApplicationError("E1001", "Network error was happened.")
        val status = getStatus(request)

        return ResponseEntity<ApplicationError>(error, status)
    }

    private fun getStatus(request: HttpServletRequest): HttpStatus {
        val statusCode: Int? = request.getAttribute("javax.servlet.error.status_code") as Int?
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR
        }
        return HttpStatus.valueOf(statusCode)
    }
}
