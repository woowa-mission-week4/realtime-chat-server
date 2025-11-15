package com.example.backend.global.exception

import org.springframework.http.ResponseEntity
import org.springframework.security.authorization.AuthorizationDeniedException
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.HttpMediaTypeNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import org.springframework.web.servlet.resource.NoResourceFoundException
import com.example.backend.global.exception.dto.DetailedExceptionResponse
import com.example.backend.global.exception.dto.ErrorSpot;
import com.example.backend.global.exception.dto.ExceptionResponse
import org.springframework.http.converter.HttpMessageNotReadableException


@RestControllerAdvice
class GlobalExceptionHandler {

    // Spring Security 권한 예외 처리
    @ExceptionHandler(AuthorizationDeniedException::class)
    fun handleAuthorizationDeniedException(
        exception: AuthorizationDeniedException
    ): ResponseEntity<ExceptionResponse> {
        return if (isUserAuthenticated()) {
            buildExceptionResponse(GlobalExceptionMessage.ACCESS_DENIED_MESSAGE)
        } else {
            buildExceptionResponse(GlobalExceptionMessage.JWT_VALIDATION_FAILED)
        }
    }

    // 이유를 알 수 없는 에러 (fallback)
    @ExceptionHandler(Exception::class)
    fun handleException(exception: Exception): ResponseEntity<ExceptionResponse> {
        return buildExceptionResponse(GlobalExceptionMessage.INTERNAL_SERVER_ERROR_MESSAGE)
    }

    // 존재하지 않는 End-Point
    @ExceptionHandler(NoResourceFoundException::class)
    fun handleNoResourceFoundException(exception: NoResourceFoundException): ResponseEntity<ExceptionResponse> {
        return buildExceptionResponse(GlobalExceptionMessage.NO_RESOURCE_MESSAGE)
    }

    // BeanValidation 유효성 검증 에러
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(exception: MethodArgumentNotValidException): ResponseEntity<ExceptionResponse> {
        val errorSpots = exception.bindingResult.fieldErrors.map { fieldError ->
            ErrorSpot(fieldError.field, fieldError.defaultMessage ?: "Invalid value")
        }
        val exceptionMessage = if (exception.bindingResult.fieldErrors.any { it.isBindingFailure }) {
            GlobalExceptionMessage.ARGUMENT_TYPE_MISMATCH_MESSAGE
        } else {
            GlobalExceptionMessage.ARGUMENT_NOT_VALID_MESSAGE
        }

        return ResponseEntity.status(exceptionMessage.httpStatus)
            .body(DetailedExceptionResponse.fail(exceptionMessage, errorSpots))
    }

    // PathVariable, RequestParam 타입 불일치
    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    fun handleMethodArgumentTypeMismatchException(exception: MethodArgumentTypeMismatchException): ResponseEntity<ExceptionResponse> {
        val type = exception.requiredType?.simpleName ?: "Unknown"
        val errorSpot = ErrorSpot(exception.name ?: "Unknown", "$type (으)로 변환할 수 없는 요청입니다.")
        return ResponseEntity.status(GlobalExceptionMessage.ARGUMENT_TYPE_MISMATCH_MESSAGE.httpStatus)
            .body(DetailedExceptionResponse.fail(GlobalExceptionMessage.ARGUMENT_TYPE_MISMATCH_MESSAGE, errorSpot))
    }

    // RequestParam 누락
    @ExceptionHandler(MissingServletRequestParameterException::class)
    fun handleMissingServletRequestParameterException(exception: MissingServletRequestParameterException): ResponseEntity<ExceptionResponse> {
        val errorSpot = ErrorSpot(exception.parameterName ?: "Unknown", exception.parameterType)
        return ResponseEntity.status(GlobalExceptionMessage.MISSING_PARAMETER_MESSAGE.httpStatus)
            .body(DetailedExceptionResponse.fail(GlobalExceptionMessage.MISSING_PARAMETER_MESSAGE, errorSpot))
    }

    // 읽을 수 없는 DTO
    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleHttpMessageNotReadableException(exception: HttpMessageNotReadableException): ResponseEntity<ExceptionResponse> {
        return buildExceptionResponse(GlobalExceptionMessage.DATA_NOT_READABLE_MESSAGE)
    }

    // Content-Type 에러
    @ExceptionHandler(HttpMediaTypeNotSupportedException::class)
    fun handleHttpMediaTypeNotSupportedException(exception: HttpMediaTypeNotSupportedException): ResponseEntity<ExceptionResponse> {
        return buildExceptionResponse(GlobalExceptionMessage.UNSUPPORTED_MEDIA_TYPE_MESSAGE)
    }

    // 비즈니스 예외
    @ExceptionHandler(BusinessException::class)
    fun handleBusinessException(exception: BusinessException): ResponseEntity<ExceptionResponse> {
        return buildExceptionResponse(exception.exceptionMessage)
    }

    private fun buildExceptionResponse(exceptionMessage: ExceptionMessage): ResponseEntity<ExceptionResponse> {
        return ResponseEntity.status(exceptionMessage.getHttpStatus())
            .body(ExceptionResponse.fail(exceptionMessage))
    }

    private fun isUserAuthenticated(): Boolean {
        val auth: Authentication? = SecurityContextHolder.getContext().authentication
        if (auth == null || !auth.isAuthenticated) return false
        return auth.principal != "anonymousUser"
    }
}
