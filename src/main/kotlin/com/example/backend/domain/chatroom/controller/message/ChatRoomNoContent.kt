package com.example.backend.domain.chatroom.controller.message

data class ChatRoomNoContent(
    val success: Boolean,
    val message: String
) {
    companion object {
        fun ok(responseMessage: ChatRoomControllerSuccessResponse): ChatRoomNoContent =
            ChatRoomNoContent(true, responseMessage.message)
    }
}
