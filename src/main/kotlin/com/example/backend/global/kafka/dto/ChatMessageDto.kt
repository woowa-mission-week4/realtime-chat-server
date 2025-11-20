package com.example.backend.global.kafka.dto

import java.time.LocalDateTime

data class ChatMessageDto(
    val roomId: String,
    val sender: String,
    val message: String,
    val sentAt: LocalDateTime = LocalDateTime.now()
)
