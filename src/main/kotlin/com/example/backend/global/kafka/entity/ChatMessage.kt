package com.example.backend.global.kafka.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "chat_message")
class ChatMessage(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(nullable = false)
    var roomId: String = "",

    @Column(nullable = false)
    var sender: String = "",

    @Column(nullable = false)
    var message: String = "",

    @Column(nullable = false)
    var sendDate: LocalDateTime = LocalDateTime.now(),

    @Enumerated(EnumType.STRING)
    var type: MessageType = MessageType.TEXT
) {
    enum class MessageType { TEXT, IMAGE, OTHER }
}
