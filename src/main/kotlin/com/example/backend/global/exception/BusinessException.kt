package com.example.backend.global.exception

open class BusinessException(
    val exceptionMessage: ExceptionMessage
) : RuntimeException(exceptionMessage.getMessage()) {

    val className: String
    val methodName: String
    val lineNumber: Int

    init {
        val stack = Thread.currentThread().stackTrace
        className = stack[2].className
        methodName = stack[2].methodName
        lineNumber = stack[2].lineNumber
    }

    fun extractExceptionLocation(): String {
        return "[${className}][${methodName}][${lineNumber}]: "
    }
}
