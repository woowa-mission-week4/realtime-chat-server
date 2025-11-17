package com.example.backend.domain.chatroom.dto.response

import com.example.backend.domain.chatroom.entity.enums.ChatRoomType

data class ChatRoomResponse (
    val id: Long,
    val chatRoomName: String,
    val description: String,
    val maxMembers: Int,
    val chatRoomType: ChatRoomType,
    val participation: Boolean,
    val userCount: Int,
    val creatorId: Long
    ) {
}
