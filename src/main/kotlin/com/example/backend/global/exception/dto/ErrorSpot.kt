package com.example.backend.global.exception.dto


data class ErrorSpot(
    val fieldName: String,
    val message: String
) {
    companion object {
        private const val MESSAGE_FORMAT = "필드명: %s, 예외 메세지: %s"
    }

    override fun toString(): String = MESSAGE_FORMAT.format(fieldName, message)
}
