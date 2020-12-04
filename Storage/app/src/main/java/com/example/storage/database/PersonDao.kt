package com.example.storage.database

import androidx.room.*

@Dao
interface PersonDao {
    @Query(
        "SELECT * FROM person WHERE personName LIKE :name LIMIT 1"
    )
    fun findByName(name: String): Person

    @Insert
    fun insertAll(vararg people: Person)

    @Transaction
    @Query("SELECT * FROM Person")
    fun getPersonsWithCars(): List<PersonWithCar>

    @Transaction
    @Query("SELECT * FROM Person where personName LIKE :person")
    fun getCarsByPersonName(person: String): List<PersonWithCar>
}