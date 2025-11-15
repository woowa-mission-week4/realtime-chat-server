package com.example.backend.global.exception

import org.springframework.http.HttpStatus

interface ExceptionMessage {
    val httpStatus: HttpStatus
    val message: String
}
