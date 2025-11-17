package com.example.backend.global.security

import com.example.backend.domain.user.exception.UserException
import com.example.backend.domain.user.exception.message.UserExceptionMessage
import com.example.backend.domain.user.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(
    private val userRepository: UserRepository
) : UserDetailsService {

    override fun loadUserByUsername(email: String): UserDetails {
        val user = userRepository.findByEmail(email)
            .orElseThrow { UserException(UserExceptionMessage.NOT_FOUND_USER) }

        return CustomUserDetails(user)
    }
}
