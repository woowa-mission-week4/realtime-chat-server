package com.example.backend.global.kafka.controller

import com.example.backend.global.kafka.dto.ChatMessageDto
import com.example.backend.global.kafka.service.ChatKafkaProducer
import com.example.backend.global.security.CustomUserDetails
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/chat")
class ChatController(
    private val chatKafkaProducer: ChatKafkaProducer
) {
    @PostMapping("/{roomId}")
    fun sendMessage(
        @PathVariable roomId: String,
        @RequestBody chatMessageDto: ChatMessageDto,
        @AuthenticationPrincipal userDetails: CustomUserDetails
    ) {
        chatKafkaProducer.sendMessage(roomId, chatMessageDto,userDetails.getUserId())
    }
}
