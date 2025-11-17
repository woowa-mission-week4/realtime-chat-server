package com.example.backend.domain.chatroom.dto.request

import com.example.backend.domain.chatroom.entity.enums.ChatRoomType
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class CreateChatRoomRequest (

    @field:NotBlank(message = "채팅방 이름은 필수 값입니다")
    val chatRoomName: String,

    @field:NotBlank(message = "채팅방 설명은 필수 값입니다")
    val description: String,

    @field:NotNull(message = "채팅방 인원 설정은 필수 값입니다")
    val maxMembers: Int,

    @field:NotNull(message = "채팅방 타입 설정은 필수입니다")
    val chatRoomType: ChatRoomType,

    )
