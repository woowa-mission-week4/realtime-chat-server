package com.example.backend.global.exception.dto

import com.example.backend.global.exception.ExceptionMessage
import io.swagger.v3.oas.annotations.media.Schema


@Schema(description = "공통 예외 응답")
open class ExceptionResponse(
    @Schema(description = "성공 여부", example = "false")
    val success: Boolean,
    @Schema(description = "에러 메시지", example = "유효하지 않은 요청입니다.")
    val message: String
) {
    companion object {
        fun fail(exceptionMessage: ExceptionMessage): ExceptionResponse =
            ExceptionResponse(false, exceptionMessage.getMessage())
    }
}

