package com.example.backend.global.kafka.service

import com.example.backend.global.kafka.dto.ChatMessageDto
import com.example.backend.global.kafka.entity.ChatMessage
import com.example.backend.global.kafka.repository.ChatMessageRepository
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class ChatKafkaConsumer(
    private val chatMessageRepository: ChatMessageRepository
) {
    @KafkaListener(
        topics = ["chat-messages"],
        groupId = "\${spring.kafka.consumer.group-id}"
    )
    fun consume(chatMessageDto: ChatMessageDto) {
        val chatMessage = ChatMessage(
            roomId = chatMessageDto.roomId,
            sender = chatMessageDto.sender,
            message = chatMessageDto.message,
            type = chatMessageDto.type,
            sendDate = chatMessageDto.sentAt
        )
        chatMessageRepository.save(chatMessage)
    }
}
