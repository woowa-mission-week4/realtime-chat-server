package com.example.backend.service

import com.example.backend.dto.AuthResponse
import com.example.backend.dto.LoginRequest
import com.example.backend.dto.SignupRequest
import com.example.backend.dto.UserResponse
import com.example.backend.entity.User
import com.example.backend.entity.UserStatus
import com.example.backend.repository.UserRepository
import com.example.backend.util.JwtUtil
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtUtil: JwtUtil,
    private val authenticationManager: AuthenticationManager,
) {
    fun signup(request: SignupRequest): AuthResponse {
        // 이메일 중복 체크
        if (userRepository.existsByEmail(request.email)) {
            throw IllegalArgumentException("이미 사용 중인 이메일입니다")
        }

        // 닉네임 중복 체크
        if (userRepository.existsByNickname(request.nickname)) {
            throw IllegalArgumentException("이미 사용 중인 닉네임입니다")
        }

        // 사용자 생성
        val user =
            User(
                email = request.email,
                password = passwordEncoder.encode(request.password),
                nickname = request.nickname,
                avatarUrl = request.avatarUrl,
                status = UserStatus.OFFLINE,
            )

        val savedUser = userRepository.save(user)

        // JWT 토큰 생성
        val token = jwtUtil.generateToken(savedUser.email)

        return AuthResponse(
            token = token,
            user = UserResponse.from(savedUser),
        )
    }

    fun login(request: LoginRequest): AuthResponse {
        // 인증
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(request.email, request.password),
        )

        // 사용자 조회
        val user =
            userRepository.findByEmail(request.email)
                .orElseThrow { IllegalArgumentException("사용자를 찾을 수 없습니다") }

        // 온라인 상태로 변경
        user.status = UserStatus.ONLINE
        val updatedUser = userRepository.save(user)

        // JWT 토큰 생성
        val token = jwtUtil.generateToken(updatedUser.email)

        return AuthResponse(
            token = token,
            user = UserResponse.from(updatedUser),
        )
    }
}
