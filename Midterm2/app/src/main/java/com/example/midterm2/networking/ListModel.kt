package com.example.midterm2.networking

data class ListModel(
    var firstname: String,
    var lastname: String,
    var skills: ArrayList<String>? = ArrayList()
)