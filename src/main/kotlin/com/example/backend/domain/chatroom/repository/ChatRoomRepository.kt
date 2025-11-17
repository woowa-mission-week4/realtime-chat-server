package com.example.backend.domain.chatroom.repository

import com.example.backend.domain.chatroom.entity.ChatRoom
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ChatRoomRepository : JpaRepository<ChatRoom, Long> {
}
