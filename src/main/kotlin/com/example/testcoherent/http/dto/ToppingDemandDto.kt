package com.example.testcoherent.http.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@Data
@NoArgsConstructor
@AllArgsConstructor
class ToppingDemandDto(

    @field:Email(message = "Invalid email")
    var email: String?,
    @field:NotEmpty(message = "Toppings should not be empty")
    var toppings: List<String>?
)