package com.example.backend.domain.user.repository

import com.example.backend.domain.user.entity.User
import com.example.backend.domain.user.entity.enums.UserStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findByEmail(email: String): Optional<User>

    fun findByNickname(nickname: String): Optional<User>

    fun existsByEmail(email: String): Boolean

    fun existsByNickname(nickname: String): Boolean

    fun findAllByStatus(status: UserStatus): List<User>
}
