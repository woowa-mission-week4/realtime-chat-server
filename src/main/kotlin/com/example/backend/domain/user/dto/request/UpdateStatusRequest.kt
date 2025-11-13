package com.example.backend.domain.user.dto.request

import com.example.backend.domain.user.entity.enums.UserStatus
import jakarta.validation.constraints.NotNull

data class UpdateStatusRequest(
    @field:NotNull(message = "상태는 필수입니다")
    val status: UserStatus,
)
