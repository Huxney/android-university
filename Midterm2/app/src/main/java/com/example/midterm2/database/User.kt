package com.example.midterm2.database

import androidx.room.*

@Entity
data class User(
    @ColumnInfo(name = "firstName") var firstName: String,
    @ColumnInfo(name = "lastName") var lastName: String,
    @ColumnInfo(name = "skills") var skills: String?
) {
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0
}