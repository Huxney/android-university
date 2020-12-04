package com.example.storage.database

import androidx.room.Embedded
import androidx.room.Relation

data class PersonWithCar(
    @Embedded val person: Person,
    @Relation(
        parentColumn = "personId",
        entityColumn = "personOwnerId"
    )
    val cars: List<Car>
)
