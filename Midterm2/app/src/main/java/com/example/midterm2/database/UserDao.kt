package com.example.midterm2.database

import androidx.room.*

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    @Query(
        "SELECT * FROM user WHERE firstName LIKE :firstName and lastName LIKE :lastName LIMIT 1"
    )
    fun findByName(firstName: String, lastName: String): User

    @Insert
    fun insertAll(vararg people: User)

    @Delete
    fun delete(user: User)

}