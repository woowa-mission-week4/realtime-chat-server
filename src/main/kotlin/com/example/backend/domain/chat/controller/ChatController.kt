package com.example.backend.domain.chat.controller

import com.example.backend.global.kafka.message.Message
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


const val KAFKA_TOPIC: String = "new-kafka-chat"

@RestController
@RequestMapping("/chat")
class ChatController(
    private val kafkaTemplate : KafkaTemplate<String, Message>
) {

    private val log: Logger = LoggerFactory.getLogger(ChatController::class.java)

    @PostMapping("/send")
    fun sendMessage(@RequestBody message: Message) {
        message.createTimestamp()
        log.info("message : ${message.text}, auth : ${message.author}")
        kafkaTemplate.send(KAFKA_TOPIC, message)
    }
}
