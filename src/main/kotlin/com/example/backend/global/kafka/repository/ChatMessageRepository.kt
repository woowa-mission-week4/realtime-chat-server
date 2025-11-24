package com.example.backend.global.kafka.repository

import com.example.backend.global.kafka.entity.ChatMessage
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ChatMessageRepository : JpaRepository<ChatMessage, Long> {
    // 필요하면 방(roomId) 별 메시지 조회 메서드도 추가 가능
    fun findAllByRoomId(roomId: String): List<ChatMessage>

}
