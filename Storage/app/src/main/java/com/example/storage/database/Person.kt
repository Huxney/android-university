package com.example.storage.database

import androidx.room.*

@Entity
data class Person(
    @PrimaryKey val personId: Long,
    val personName: String
)