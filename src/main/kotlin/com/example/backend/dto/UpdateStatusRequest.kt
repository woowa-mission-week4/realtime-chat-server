package com.example.backend.dto

import com.example.backend.entity.UserStatus
import jakarta.validation.constraints.NotNull

data class UpdateStatusRequest(
    @field:NotNull(message = "상태는 필수입니다")
    val status: UserStatus,
)
