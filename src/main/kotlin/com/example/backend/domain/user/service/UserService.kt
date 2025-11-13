package com.example.backend.domain.user.service

import com.example.backend.domain.user.dto.request.UpdateProfileRequest
import com.example.backend.domain.user.dto.request.UpdateStatusRequest
import com.example.backend.domain.user.dto.response.UserResponse
import com.example.backend.domain.user.entity.enums.UserStatus
import com.example.backend.domain.user.repository.UserRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
) {
    fun getCurrentUser(): UserResponse {
        val email = getCurrentUserEmail()
        val user =
            userRepository.findByEmail(email)
                .orElseThrow { IllegalArgumentException("사용자를 찾을 수 없습니다") }
        return UserResponse.from(user)
    }

    fun updateProfile(request: UpdateProfileRequest): UserResponse {
        val email = getCurrentUserEmail()
        val user =
            userRepository.findByEmail(email)
                .orElseThrow { IllegalArgumentException("사용자를 찾을 수 없습니다") }

        // 닉네임 변경 시 중복 체크
        if (request.nickname != null && request.nickname != user.nickname) {
            if (userRepository.existsByNickname(request.nickname)) {
                throw IllegalArgumentException("이미 사용 중인 닉네임입니다")
            }
            user.nickname = request.nickname
        }

        // 아바타 URL 변경
        if (request.avatarUrl != null) {
            user.avatarUrl = request.avatarUrl
        }

        val updatedUser = userRepository.save(user)
        return UserResponse.from(updatedUser)
    }

    fun updateStatus(request: UpdateStatusRequest): UserResponse {
        val email = getCurrentUserEmail()
        val user =
            userRepository.findByEmail(email)
                .orElseThrow { IllegalArgumentException("사용자를 찾을 수 없습니다") }

        user.status = request.status
        val updatedUser = userRepository.save(user)
        return UserResponse.from(updatedUser)
    }

    fun getOnlineUsers(): List<UserResponse> {
        return userRepository.findAllByStatus(UserStatus.ONLINE)
            .map { UserResponse.from(it) }
    }

    fun getUserById(userId: Long): UserResponse {
        val user =
            userRepository.findById(userId)
                .orElseThrow { IllegalArgumentException("사용자를 찾을 수 없습니다") }
        return UserResponse.from(user)
    }

    private fun getCurrentUserEmail(): String {
        return SecurityContextHolder.getContext().authentication.name
    }
}
