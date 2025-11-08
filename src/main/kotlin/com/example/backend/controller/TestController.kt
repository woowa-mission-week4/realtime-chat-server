package com.example.backend.controller

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {
    private val log: Logger = LoggerFactory.getLogger(TestController::class.java)

    // 간결화된 함수문
    @RequestMapping("/test")
    fun hello(): ResponseEntity<String> = ResponseEntity.ok("Hello World!")

    // 전형적인 함수
    @RequestMapping("/test2")
    fun hello2(): ResponseEntity<String> {
        return ResponseEntity.ok("Hello World!")
    }

    // 반환타입이 Any (Object 대체)
    @RequestMapping("/test3")
    fun hello3(): Any {
        return ResponseEntity.ok("Hello World!")
    }

    // 반환 타입이 Void
    @RequestMapping("/test4")
    @ResponseStatus(HttpStatus.OK)
    fun hello4() {
        log.info("Hello World!")
    }
}
