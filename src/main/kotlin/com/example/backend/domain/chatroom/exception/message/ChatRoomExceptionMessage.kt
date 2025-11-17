package com.example.backend.domain.chatroom.exception.message

import com.example.backend.global.exception.ExceptionMessage
import org.springframework.http.HttpStatus

enum class ChatRoomExceptionMessage (
    override val httpStatus: HttpStatus,
    override val message: String
) : ExceptionMessage {

    INVALID_CHATROOM_NAME(HttpStatus.BAD_REQUEST, "채팅방 이름은 2자 ~  20자로 구성되어야 합니다"),
    INVALID_CHATROOM_DESCRIPTION(HttpStatus.BAD_REQUEST, "채팅방 설명은 최대 150자로 구성되어야 합니다"),
    INVALID_CHATROOM_PARTICIPANTS(HttpStatus.BAD_REQUEST, "채팅방 최대 인원은 100명입니다"),
    NOT_FOUND_CHATROOM(HttpStatus.NOT_FOUND, "채팅방을 찾을 수 없습니다"),
    ALREADY_JOINED(HttpStatus.CONFLICT, "이미 참여중인 채팅방입니다"),
    NOT_JOINED(HttpStatus.BAD_REQUEST, "참여 중인 채팅방이아닙니다"),

    REQUIRED_CHATROOM_NAME(HttpStatus.UNPROCESSABLE_ENTITY, "채팅방 이름이 null 값입니다. 파라미터를 확인해주세요 "),
    REQUIRED_CHATROOM_DESCRIPTION(HttpStatus.UNPROCESSABLE_ENTITY, "채팅방 설명이 null 값입니다. 파라미터를 확인해주세요")

    }

