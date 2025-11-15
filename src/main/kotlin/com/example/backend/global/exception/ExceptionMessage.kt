package com.example.backend.global.exception

import org.springframework.http.HttpStatus

interface ExceptionMessage {
    fun getHttpStatus(): HttpStatus
    fun getMessage(): String
}
