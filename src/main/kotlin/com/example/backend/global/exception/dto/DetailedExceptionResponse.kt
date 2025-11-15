package com.example.backend.global.exception.dto

import com.example.backend.global.exception.ExceptionMessage
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "상세 에러 정보 포함 예외 응답")
class DetailedExceptionResponse(
    success: Boolean,
    message: String,
    @Schema(description = "상세 에러 정보")
    val errors: List<ErrorSpot>
) : ExceptionResponse(success, message) {

    companion object {
        fun fail(exceptionMessage: ExceptionMessage, errors: List<ErrorSpot>): DetailedExceptionResponse =
            DetailedExceptionResponse(false, exceptionMessage.getMessage(), errors)

        fun fail(exceptionMessage: ExceptionMessage, error: ErrorSpot): DetailedExceptionResponse =
            DetailedExceptionResponse(false, exceptionMessage.getMessage(), listOf(error))
    }
}
