package com.example.backend.domain.user.controller

import com.example.backend.domain.user.dto.request.UpdateProfileRequest
import com.example.backend.domain.user.dto.request.UpdateStatusRequest
import com.example.backend.domain.user.dto.response.UserResponse
import com.example.backend.domain.user.service.UserService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/users")
class UserController(
    private val userService: UserService,
) {
    @GetMapping("/me")
    fun getCurrentUser(): ResponseEntity<UserResponse> {
        val user = userService.getCurrentUser()
        return ResponseEntity.ok(user)
    }

    @PutMapping("/me/profile")
    fun updateProfile(
        @Valid @RequestBody request: UpdateProfileRequest,
    ): ResponseEntity<UserResponse> {
        val user = userService.updateProfile(request)
        return ResponseEntity.ok(user)
    }

    @PutMapping("/me/status")
    fun updateStatus(
        @Valid @RequestBody request: UpdateStatusRequest,
    ): ResponseEntity<UserResponse> {
        val user = userService.updateStatus(request)
        return ResponseEntity.ok(user)
    }

    @GetMapping("/online")
    fun getOnlineUsers(): ResponseEntity<List<UserResponse>> {
        val users = userService.getOnlineUsers()
        return ResponseEntity.ok(users)
    }

    @GetMapping("/{userId}")
    fun getUserById(
        @PathVariable userId: Long,
    ): ResponseEntity<UserResponse> {
        val user = userService.getUserById(userId)
        return ResponseEntity.ok(user)
    }
}
