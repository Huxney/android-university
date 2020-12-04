package com.example.storage.database

import androidx.room.*

@Dao
interface CarDao {
    @Insert
    fun insertAll(vararg car: Car)
}