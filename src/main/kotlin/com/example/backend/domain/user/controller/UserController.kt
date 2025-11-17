package com.example.backend.domain.user.controller

import com.example.backend.domain.user.controller.message.UserControllerSuccessResponse
import com.example.backend.domain.user.dto.request.UpdateProfileRequest
import com.example.backend.domain.user.dto.request.UpdateStatusRequest
import com.example.backend.domain.user.dto.response.UserResponse
import com.example.backend.domain.user.service.UserService
import com.example.backend.global.ApiTemplate
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
    fun getCurrentUser(): ApiTemplate<UserResponse> {
        val user = userService.getCurrentUser()
        return ApiTemplate.ok(UserControllerSuccessResponse.LOAD_MY_PROFILE_SUCCESS, user)
    }

    @PutMapping("/me/profile")
    fun updateProfile(
        @Valid @RequestBody request: UpdateProfileRequest,
    ): ApiTemplate<UserResponse> {
        val user = userService.updateProfile(request)
        return ApiTemplate.ok(UserControllerSuccessResponse.UPDATE_MY_PROFILE_SUCCESS, user)
    }

    @PutMapping("/me/status")
    fun updateStatus(
        @Valid @RequestBody request: UpdateStatusRequest,
    ): ApiTemplate<UserResponse> {
        val user = userService.updateStatus(request)
        return ApiTemplate.ok(UserControllerSuccessResponse.CHANGE_MY_STATUS, user)
    }

    @GetMapping("/online")
    fun getOnlineUsers(): ApiTemplate<List<UserResponse>> {
        val users = userService.getOnlineUsers()
        return ApiTemplate.ok(UserControllerSuccessResponse.LOAD_ONLINE_USERS, users)
    }

    @GetMapping("/{userId}")
    fun getUserById(
        @PathVariable userId: Long,
    ):ApiTemplate<UserResponse> {
        val user = userService.getUserById(userId)
        return ApiTemplate.ok(UserControllerSuccessResponse.LOAD_ALL_USERS, user)
    }
}
