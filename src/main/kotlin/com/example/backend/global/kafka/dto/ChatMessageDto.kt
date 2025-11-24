package com.example.backend.global.kafka.dto

import com.example.backend.global.kafka.entity.ChatMessage
import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

data class ChatMessageDto(
    val roomId: String = "",
    val sender: String = "",
    val message: String = "",
    val type: ChatMessage.MessageType = ChatMessage.MessageType.TEXT,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    val sentAt: LocalDateTime = LocalDateTime.now()
)
