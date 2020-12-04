package com.example.storage.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Car(
    @PrimaryKey val carId: Long,
    val personOwnerId: Long,
    val carName: String
)