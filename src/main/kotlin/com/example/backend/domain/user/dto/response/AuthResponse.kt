package com.example.backend.domain.user.dto.response

data class AuthResponse(
    val token: String,
    val user: UserResponse,
)
