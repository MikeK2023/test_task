package com.example.testcoherent.repository

import com.example.testcoherent.model.ToppingDemand
import com.example.testcoherent.model.ToppingRating
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ToppingDemandRepo : JpaRepository<ToppingDemand, Long> {

    fun findAllByEmail(email: String?): List<ToppingDemand>

    @Query("SELECT toppingName AS name, COUNT(DISTINCT email) AS rating FROM ToppingDemand GROUP BY toppingName")
    fun findRating(): List<ToppingRating>
}