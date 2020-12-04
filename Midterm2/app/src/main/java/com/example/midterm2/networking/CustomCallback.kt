package com.example.midterm2.networking

interface CustomCallback {
    fun onSuccess(body: String) {}
    fun onFailed(error: String) {}
}