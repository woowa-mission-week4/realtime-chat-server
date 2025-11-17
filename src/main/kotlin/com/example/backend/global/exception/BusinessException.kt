package com.example.backend.global.exception

open class BusinessException(
    val exceptionMessage: ExceptionMessage
) : RuntimeException(exceptionMessage.message) {

    val className: String = Thread.currentThread().stackTrace[2].className
    val methodName: String = Thread.currentThread().stackTrace[2].methodName
    val lineNumber: Int = Thread.currentThread().stackTrace[2].lineNumber

    fun extractExceptionLocation(): String =
        "[${className}][${methodName}][${lineNumber}]: "
}
