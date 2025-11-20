package com.example.backend.global.kafka.Implement

import com.example.backend.global.kafka.dto.ChatMessageDto
import com.example.backend.global.kafka.entity.ChatMessage
import com.example.backend.global.kafka.repository.ChatMessageRepository
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.SendResult
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.concurrent.CompletableFuture

@Service
class ChatKafkaProducer(
    private val kafkaTemplate: KafkaTemplate<String, ChatMessageDto>,
    private val chatMessageRepository: ChatMessageRepository,
    private val appName: String
) {
/*
    fun sendMessage(roomId: String, chatMessageDto: ChatMessageDto) {
        // Kafka로 메시지 전송
        val topicName = appName
        println("메시지 보낸 토픽 이름 $topicName: 보낸 메시지 $chatMessageDto")

        val future: CompletableFuture<SendResult<String, ChatMessageDto>> =
            kafkaTemplate.send(topicName, roomId, chatMessageDto).completable()

        future.whenComplete { result, ex ->
            if (ex == null) {
                println("메시지가 $topicName에 잘 전송되었습니다.")

                // 메시지 전송 성공 시 DB에 저장
                val chatMessage = ChatMessage().apply {
                    this.roomId = chatMessageDto.roomId
                    this.sender = chatMessageDto.sender
                    this.message = chatMessageDto.message
                    this.type = ChatMessage.MessageType.valueOf(chatMessageDto.type.name)
                    this.sendDate = LocalDateTime.now()
                }
                chatMessageRepository.save(chatMessage)
                println("$chatMessage가 잘 저장되었습니다.")
            } else {
                println("Kafka 토픽에 메시지 전송 실패 $topicName: $chatMessageDto, 이유: $ex")
            }
        }
    }*/
}
