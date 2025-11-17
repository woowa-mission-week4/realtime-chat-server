package com.example.backend.global

data class ApiTemplate<T>(
    val success: Boolean,
    val message: String,
    val result: T? = null
) {
    companion object {
        fun <T> ok(responseMessage: ResponseMessage): ApiTemplate<T> =
            ApiTemplate(true, responseMessage.message, null)

        fun <T> ok(responseMessage: Enum<*>, result: T): ApiTemplate<T> =
            ApiTemplate(true, (responseMessage as ResponseMessage).message, result)
    }
}
