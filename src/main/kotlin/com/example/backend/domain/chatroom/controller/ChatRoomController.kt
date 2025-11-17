package com.example.backend.domain.chatroom.controller

import com.example.backend.domain.chatroom.controller.message.ChatRoomControllerSuccessResponse
import com.example.backend.domain.chatroom.dto.request.CreateChatRoomRequest
import com.example.backend.domain.chatroom.dto.response.ChatRoomResponse
import com.example.backend.domain.chatroom.service.ChatRoomService
import com.example.backend.domain.user.service.UserService
import com.example.backend.global.ApiTemplate
import com.example.backend.domain.chatroom.controller.message.ChatRoomNoContent
import com.example.backend.global.security.CustomUserDetails
import jakarta.validation.Valid
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/chatrooms")
class ChatRoomController (
    private val chatRoomService: ChatRoomService,
    private val userService: UserService
) {
    @GetMapping("/all")
    fun getAllChatRooms(): ApiTemplate<List<ChatRoomResponse>> {
        val chatrooms = chatRoomService.getAllChatRooms()
        return ApiTemplate.ok(ChatRoomControllerSuccessResponse.LOAD_ALL_CHAT_ROOMS_SUCCESS, chatrooms)
    }

    @GetMapping("/{id}")
    fun getChatRoom(@PathVariable("id") id: Long): ApiTemplate<ChatRoomResponse> {
        val chatroom = chatRoomService.getChatRoom(id);
        return ApiTemplate.ok(ChatRoomControllerSuccessResponse.LOAD_CHAT_ROOM_SUCCESS, chatroom)
    }

    @PostMapping("/create")
    fun createChatRoom(@Valid @RequestBody chatRoomRequest: CreateChatRoomRequest,
                       @AuthenticationPrincipal userDetails: CustomUserDetails
    ): ApiTemplate<ChatRoomResponse> {
        val createChatRoom = chatRoomService.createChatRoom(userDetails.getUserId(), chatRoomRequest)
        return ApiTemplate.ok(ChatRoomControllerSuccessResponse.CREATE_CHAT_ROOM_SUCCESS, createChatRoom)
    }

    @PatchMapping("/update/name/{id}")
    fun updateChatRoomName(
        @PathVariable("id") id: Long,
        @RequestParam("chatRoomName") chatRoomName: String,
        @AuthenticationPrincipal userDetails: CustomUserDetails
    ): ChatRoomNoContent {
        chatRoomService.updateChatRoomName(id, userDetails.getUserId(), chatRoomName)
        return ChatRoomNoContent.ok(ChatRoomControllerSuccessResponse.UPDATE_CHAT_ROOM_NAME_SUCCESS)
    }

    @PatchMapping("/update/description/{id}")
    fun updateChatRoomDescription(
        @PathVariable("id") id: Long,
        @RequestParam("chatDescription") chatDescription: String,
        @AuthenticationPrincipal userDetails: CustomUserDetails
    ): ChatRoomNoContent {
        chatRoomService.updateChatRoomDescription(id, userDetails.getUserId(), chatDescription)
        return ChatRoomNoContent.ok(ChatRoomControllerSuccessResponse.UPDATE_CHAT_ROOM_DESCRIPTION_SUCCESS)
    }

    @DeleteMapping("/{id}")
    fun deleteChatRoom(
        @PathVariable("id") id: Long,
        @AuthenticationPrincipal userDetails: CustomUserDetails
    ): ChatRoomNoContent {
        chatRoomService.deleteChatRoom(id, userDetails.getUserId())
        return ChatRoomNoContent.ok(ChatRoomControllerSuccessResponse.DELETE_CHAT_ROOM_SUCCESS)
    }

    @PostMapping("/join/{id}")
    fun joinChatRoom(
        @PathVariable("id") id: Long,
        @AuthenticationPrincipal userDetails: CustomUserDetails
    ): ChatRoomNoContent {
        chatRoomService.joinChatRoom(id, userDetails.getUserId())
        return ChatRoomNoContent.ok(ChatRoomControllerSuccessResponse.JOIN_CHAT_ROOM_SUCCESS)
    }

    @PostMapping("/leave/{id}")
    fun leaveChatRoom(
        @PathVariable("id") id: Long,
        @AuthenticationPrincipal userDetails: CustomUserDetails
    ): ChatRoomNoContent {
        chatRoomService.leaveChatRoom(id, userDetails.getUserId())
        return ChatRoomNoContent.ok(ChatRoomControllerSuccessResponse.LEAVE_CHAT_ROOM_SUCCESS)
    }



}
