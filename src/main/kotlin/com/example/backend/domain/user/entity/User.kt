package com.example.backend.domain.user.entity

import com.example.backend.domain.chatroom.entity.ChatRoom
import com.example.backend.domain.user.entity.enums.UserStatus
import com.example.backend.global.entity.BaseEntity
import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener::class)
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(unique = true, nullable = false)
    val email: String,

    @Column(nullable = false)
    val password: String,

    @Column(unique = true, nullable = false)
    var nickname: String,

    @Column
    var avatarUrl: String? = null,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var status: UserStatus = UserStatus.OFFLINE,


    @ManyToMany(mappedBy = "users", fetch = FetchType.LAZY)
    var chatRooms: MutableList<ChatRoom> = mutableListOf(),

    ) : BaseEntity() {

}
