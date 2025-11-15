package com.example.backend.global.kafka.message

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

data class Message (
    @JsonProperty("author") val author: String?,
    @JsonProperty("text") val text: String,
    @JsonProperty("timestamp") var timestamp: String?
){
    fun createTimestamp() {
        this.timestamp = LocalDateTime.now().toString()
    }
}
