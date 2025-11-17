package com.example.backend.domain.chatroom.controller.message

import com.example.backend.global.config.ResponseMessage

enum class ChatRoomControllerSuccessResponse (
    override val message: String
) : ResponseMessage {
    LOAD_ALL_CHAT_ROOMS_SUCCESS("채팅방 전체 조회에 성공하였습니다"),
    LOAD_CHAT_ROOM_SUCCESS("채팅방 상세 조회에 성공하였습니다"),
    CREATE_CHAT_ROOM_SUCCESS("채팅방 생성에 성공하였습니다"),
    UPDATE_CHAT_ROOM_NAME_SUCCESS("채팅방 이름을 수정되었습니다 "),
    UPDATE_CHAT_ROOM_DESCRIPTION_SUCCESS("채팅방 설명이 수정되었습니다 "),
    DELETE_CHAT_ROOM_SUCCESS("채팅방이 삭제되었습니다"),
    JOIN_CHAT_ROOM_SUCCESS("채팅방에 입장했습니다"),
    LEAVE_CHAT_ROOM_SUCCESS("채팅방에서 나갔습니다"),
}
