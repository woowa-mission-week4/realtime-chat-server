package com.example.backend.dto

import jakarta.validation.constraints.Size

data class UpdateProfileRequest(
    @field:Size(min = 2, max = 20, message = "닉네임은 2자 이상 20자 이하여야 합니다")
    val nickname: String?,
    val avatarUrl: String?,
)
