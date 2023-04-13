package com.example.testcoherent.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "topping_demand")
class ToppingDemand(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Access(AccessType.PROPERTY)
    var id: Long = 0,

    @Column(name = "email", nullable = false, length = 255)
    var email: String,

    @Column(name = "topping_name", nullable = false, length = 255)
    var toppingName: String,

    @Column(name = "created", nullable = false)
    var created: LocalDateTime,

    @Column(name = "updated", nullable = false)
    var updated: LocalDateTime
) {
    constructor(email: String, toppingName: String) : this(0, email, toppingName, LocalDateTime.now(), LocalDateTime.now())

    @PrePersist
    fun onInsert() {
        created = LocalDateTime.now()
        updated = LocalDateTime.now()
    }

    @PreUpdate
    fun onUpdate() {
        updated = LocalDateTime.now()
    }
}

interface ToppingRating {
    var name: String
    var rating: Long
}