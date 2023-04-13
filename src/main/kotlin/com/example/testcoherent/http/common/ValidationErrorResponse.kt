package com.example.testcoherent.http.common

import lombok.Data
import lombok.Value

@Data
class ValidationErrorResponse {
    var violations: MutableList<Violation> = mutableListOf()
}

@Value
class Violation(
    var fieldName: String? = null,
    var message: String? = null
)