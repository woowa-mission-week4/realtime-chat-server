package com.example.backend.global.kafka.service

import com.example.backend.domain.user.entity.User
import com.example.backend.domain.user.exception.UserException
import com.example.backend.domain.user.exception.message.UserExceptionMessage
import com.example.backend.domain.user.repository.UserRepository
import com.example.backend.global.kafka.dto.ChatMessageDto
import com.example.backend.global.kafka.entity.ChatMessage
import com.example.backend.global.kafka.repository.ChatMessageRepository
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service


@Service
class ChatKafkaProducer(
    private val kafkaTemplate: KafkaTemplate<String, ChatMessageDto>,
    private val chatMessageRepository: ChatMessageRepository,
    private val userRepository: UserRepository
) {
    private val topicName = "chat-messages"

    fun sendMessage(roomId: String, chatMessageDto: ChatMessageDto, userId: Long) {
        try {
            val user = findUser(userId)
            val messageWithSender = chatMessageDto.copy(sender = (user.id).toString())
            // Kafka 전송 (동기)
            kafkaTemplate.send(topicName, roomId, messageWithSender).get()

            // 전송 성공 시 DB 저장
            val chatMessage = ChatMessage(
                roomId = messageWithSender.roomId,
                sender = messageWithSender.sender,
                message = messageWithSender.message,
                type = messageWithSender.type,
                sendDate = messageWithSender.sentAt
            )
            chatMessageRepository.save(chatMessage)
        } catch (ex: Exception) {
            println("Kafka 전송 실패: $ex")
        }
}

    fun findUser(userId: Long): User =
        userRepository.findById(userId)
            .orElseThrow{ UserException(UserExceptionMessage.NOT_FOUND_USER) }

}
