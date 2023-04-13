package com.example.testcoherent.http

import com.example.testcoherent.http.common.ApiResponse
import com.example.testcoherent.http.dto.ToppingDemandDto
import com.example.testcoherent.http.dto.ToppingRatingDto
import com.example.testcoherent.model.ToppingRating
import com.example.testcoherent.service.ToppingDemandService
import jakarta.validation.Valid
import lombok.RequiredArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/toppings")
@RequiredArgsConstructor
@Validated
class ToppingDemandCtrl(private val toppingDemandService: ToppingDemandService) {

    @GetMapping("/rating")
    fun getRating() = toppingDemandService.getDemandRating()
        .map { it.toDto() }
        .sortedByDescending { it.customerCount }
        .ok()


    @PostMapping
    fun submit(@RequestBody @Valid request: ToppingDemandDto): ResponseEntity<Void> {
        toppingDemandService.submitRequest(request)
        return ResponseEntity.accepted().build()
    }

    private fun ToppingRating.toDto() = ToppingRatingDto(
        toppingName = name,
        customerCount = rating
    )

    fun <T> T.ok(): ApiResponse<T> = ApiResponse.ok(this)

}
