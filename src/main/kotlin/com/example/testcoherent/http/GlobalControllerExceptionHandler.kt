package com.example.testcoherent.http

import com.example.testcoherent.http.common.ApiResponse
import com.example.testcoherent.http.common.ApiResponse.Companion.error
import com.example.testcoherent.http.common.ValidationErrorResponse
import com.example.testcoherent.http.common.Violation
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalControllerExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun onMethodArgumentNotValidException(e: MethodArgumentNotValidException):
            ResponseEntity<ApiResponse<ValidationErrorResponse>?> {
        val error = ValidationErrorResponse()
        for (fieldError in e.bindingResult.fieldErrors) {
            error.violations.add(
                Violation(fieldError.field, fieldError.defaultMessage)
            )
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .contentType(MediaType.APPLICATION_JSON)
            .body(error(error, DEFAULT_ERROR_MESSAGE))
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(ex: IllegalArgumentException): ResponseEntity<ApiResponse<*>?> {
        log.error("Error occurred: {}", ex.message, ex)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .contentType(MediaType.APPLICATION_JSON)
            .body(error<Any>(ex.message))
    }

    @ExceptionHandler(RuntimeException::class)
    fun handleRuntimeException(ex: RuntimeException): ResponseEntity<ApiResponse<*>?> {
        log.error("Error occurred: {}", ex.message, ex)
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .contentType(MediaType.APPLICATION_JSON)
            .body(error<Any>(DEFAULT_ERROR_MESSAGE))
    }

    companion object {
        const val DEFAULT_ERROR_MESSAGE = "Application error"
        val log: Logger = LoggerFactory.getLogger(GlobalControllerExceptionHandler::class.java)
    }
}