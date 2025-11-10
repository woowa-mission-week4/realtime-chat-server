package com.example.backend.dto

import com.example.backend.entity.User
import com.example.backend.entity.UserStatus
import java.time.LocalDateTime

data class UserResponse(
    val id: Long,
    val email: String,
    val nickname: String,
    val avatarUrl: String?,
    val status: UserStatus,
    val createdAt: LocalDateTime,
) {
    companion object {
        fun from(user: User): UserResponse {
            return UserResponse(
                id = user.id!!,
                email = user.email,
                nickname = user.nickname,
                avatarUrl = user.avatarUrl,
                status = user.status,
                createdAt = user.createdAt,
            )
        }
    }
}
