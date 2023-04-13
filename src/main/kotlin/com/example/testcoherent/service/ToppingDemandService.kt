package com.example.testcoherent.service

import com.example.testcoherent.http.dto.ToppingDemandDto
import com.example.testcoherent.model.ToppingDemand
import com.example.testcoherent.model.ToppingRating
import com.example.testcoherent.repository.ToppingDemandRepo
import lombok.RequiredArgsConstructor
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@RequiredArgsConstructor
class ToppingDemandService(private val repo: ToppingDemandRepo) {

    private companion object {
        private val log: Logger = LoggerFactory.getLogger(ToppingDemandService::class.java)
    }

    fun getDemandRating(): List<ToppingRating> = repo.findRating()

    @Transactional
    fun submitRequest(request: ToppingDemandDto) {

        val email = request.email
        validateSubmitRequest(request)

        val foundInDb = repo.findAllByEmail(email)
        if (foundInDb.isNotEmpty()) repo.deleteAll(foundInDb)

        val toppingDemands = request.toppings!!.map { ToppingDemand(email = email!!, toppingName = it) }

        repo.saveAll(toppingDemands)

        log.info("Saved request for $email")
    }

    private fun validateSubmitRequest(request: ToppingDemandDto) {
        if (request.email.isNullOrBlank()) throw IllegalArgumentException("Invalid email")
        if (request.toppings.isNullOrEmpty()) throw IllegalArgumentException("Toppings should not be empty")
    }
}
