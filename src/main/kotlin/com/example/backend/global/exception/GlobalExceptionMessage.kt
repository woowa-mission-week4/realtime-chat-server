package com.example.backend.global.exception

import org.springframework.http.HttpStatus

enum class GlobalExceptionMessage(
    override val httpStatus: HttpStatus,
    override val message: String
) : ExceptionMessage {
    INTERNAL_SERVER_ERROR_MESSAGE(HttpStatus.INTERNAL_SERVER_ERROR, "알 수 없는 서버 에러가 발생했습니다."),
    NO_RESOURCE_MESSAGE(HttpStatus.NOT_FOUND, "존재하지 않는 경로입니다."),
    ARGUMENT_NOT_VALID_MESSAGE(HttpStatus.BAD_REQUEST, "응답 데이터의 유효성 검증이 실패했습니다."),
    ARGUMENT_TYPE_MISMATCH_MESSAGE(HttpStatus.BAD_REQUEST, "경로 변수 또는 쿼리 파라미터의 타입이 잘못되었습니다."),
    MISSING_PARAMETER_MESSAGE(HttpStatus.BAD_REQUEST, "필수 쿼리 파라미터가 누락되었습니다."),
    DATA_NOT_READABLE_MESSAGE(HttpStatus.BAD_REQUEST, "읽을 수 없는 응답 데이터입니다."),
    UNSUPPORTED_MEDIA_TYPE_MESSAGE(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "지원되지 않는 content-type 입니다."),
    UNPROCESSABLE_ENTITY(HttpStatus.UNPROCESSABLE_ENTITY, "서버에서 본문을 처리할 수 없습니다."),
    AUTH_NOT_FOUND(HttpStatus.INTERNAL_SERVER_ERROR, "시큐리티 인증 정보를 찾을 수 없습니다."),
    ACCESS_DENIED_MESSAGE(HttpStatus.FORBIDDEN, "접근이 거부되었습니다."),
    JWT_CREATION_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "JWT 토큰 생성에 실패했습니다."),
    JWT_PARSING_FAILED(HttpStatus.UNAUTHORIZED, "JWT 토큰 파싱에 실패했습니다."),
    JWT_VALIDATION_FAILED(HttpStatus.UNAUTHORIZED, "JWT 토큰 유효성 검증에 실패했습니다."),
    JWT_TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "JWT 토큰이 만료되었습니다.")
}
