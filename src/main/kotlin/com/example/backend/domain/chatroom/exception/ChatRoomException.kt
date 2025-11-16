package com.example.backend.domain.chatroom.exception

import com.example.backend.global.exception.BusinessException
import com.example.backend.global.exception.ExceptionMessage

class ChatRoomException(
    exceptionMessage: ExceptionMessage
) : BusinessException(exceptionMessage)
