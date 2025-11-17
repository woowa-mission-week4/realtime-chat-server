package com.example.backend.domain.user.controller

import com.example.backend.domain.user.controller.message.UserControllerSuccessResponse
import com.example.backend.domain.user.dto.response.AuthResponse
import com.example.backend.domain.user.dto.request.LoginRequest
import com.example.backend.domain.user.dto.request.SignupRequest
import com.example.backend.domain.user.service.AuthService
import com.example.backend.global.ApiTemplate
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val authService: AuthService,
) {
    @PostMapping("/signup")
    fun signup(
        @Valid @RequestBody request: SignupRequest,
    ): ApiTemplate<AuthResponse> {
        val response = authService.signup(request)
        return ApiTemplate.ok(UserControllerSuccessResponse.SIGNUP_SUCCESS ,response)
    }

    @PostMapping("/login")
    fun login(
        @Valid @RequestBody request: LoginRequest,
    ): ApiTemplate<AuthResponse> {
        val response = authService.login(request)
        return ApiTemplate.ok(UserControllerSuccessResponse.LOGIN_SUCCESS ,response)
    }
}
