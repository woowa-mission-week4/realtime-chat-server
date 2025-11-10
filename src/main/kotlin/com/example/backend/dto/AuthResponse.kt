package com.example.backend.dto

data class AuthResponse(
    val token: String,
    val user: UserResponse,
)
