package com.example.backend.domain.user.exception.message

import com.example.backend.global.exception.ExceptionMessage
import org.springframework.http.HttpStatus

enum class UserExceptionMessage(
    private val status: HttpStatus,
    private val msg: String
) : ExceptionMessage {

    NOT_FOUND_USER(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다"),
    NICKNAME_ALREADY_USE(HttpStatus.BAD_REQUEST, "이미 사용 중인 닉네임입니다"),
    EMAIL_ALREADY_USE(HttpStatus.BAD_REQUEST, "이미 사용 중인  이메일입니다");




    override fun getHttpStatus(): HttpStatus = status
    override fun getMessage(): String = msg
}
