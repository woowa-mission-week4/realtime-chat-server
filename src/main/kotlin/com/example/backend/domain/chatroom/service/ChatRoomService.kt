package com.example.backend.domain.chatroom.service

import com.example.backend.domain.chatroom.dto.request.CreateChatRoomRequest
import com.example.backend.domain.chatroom.dto.response.ChatRoomResponse
import com.example.backend.domain.chatroom.entity.ChatRoom
import com.example.backend.domain.chatroom.entity.enums.ChatRoomType
import org.springframework.transaction.annotation.Transactional
import com.example.backend.domain.chatroom.exception.ChatRoomException
import com.example.backend.domain.chatroom.exception.message.ChatRoomExceptionMessage
import com.example.backend.domain.chatroom.repository.ChatRoomRepository
import com.example.backend.domain.user.entity.User
import com.example.backend.domain.user.exception.UserException
import com.example.backend.domain.user.exception.message.UserExceptionMessage
import com.example.backend.domain.user.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class ChatRoomService (
    private val chatRoomRepository: ChatRoomRepository,
    private val userRepository: UserRepository
) {
    // 채팅방 전체 조회
    @Transactional(readOnly = true)
    fun getAllChatRooms(): List<ChatRoomResponse> {
        val rooms = chatRoomRepository.findAll()
        return if (rooms.isEmpty()) emptyList() else rooms.map { it.toResponse() }
    }

    // 채팅방 상세 조회
    @Transactional(readOnly = true)
    fun getChatRoom(chatRoomId: Long): ChatRoomResponse {
        val chatRoom = chatRoomRepository.findById(chatRoomId)
            .orElseThrow { ChatRoomException(ChatRoomExceptionMessage.NOT_FOUND_CHATROOM) }
        return chatRoom.toResponse()
    }

    //채팅방 생성
    @Transactional
    fun createChatRoom(
        userId: Long,
        request: CreateChatRoomRequest
    ): ChatRoomResponse {
        val creator = userRepository.findById(userId)
            .orElseThrow { UserException(UserExceptionMessage.NOT_FOUND_USER) }

        val maxMembers = if (request.chatRoomType == ChatRoomType.PERSONAL) 2 else request.maxMembers

        val chatRoom = ChatRoom(
            creatorId = creator.id,
            chatRoomName = request.chatRoomName,
            description = request.description,
            maxMembers = maxMembers,
            chatRoomType = request.chatRoomType,
            participation = true
        )

        chatRoom.addUser(creator)
        val savedChatRoom = chatRoomRepository.save(chatRoom)

        return savedChatRoom.toResponse()
    }
    //채팅방 이름 수정
    @Transactional
    fun updateChatRoomName(
        chatRoomId: Long,
        userId: Long,
        chatRoomName: String
    ) {
        val chatRoom = findChatRoom(chatRoomId)
        checkPermission(chatRoomId, userId)
        chatRoom.updateName(chatRoomName)

    }

    //채팅방 설명 수정
    @Transactional
    fun updateChatRoomDescription(
        chatRoomId: Long,
        userId: Long,
        chatRoomDescription: String
    ) {
        val chatRoom = findChatRoom(chatRoomId)
        checkPermission(chatRoomId, userId)
        chatRoom.updateDescription(chatRoomDescription)
    }

    //채팅방 삭제
    @Transactional
    fun deleteChatRoom(
        chatRoomId: Long,
        userId: Long) {
        val chatRoom = findChatRoom(chatRoomId)
        checkPermission(chatRoomId, userId)
        chatRoomRepository.delete(chatRoom)
    }

    //채팅방 입장
    @Transactional
    fun joinChatRoom(chatRoomId: Long, userId: Long) {
        val chatRoom = findChatRoom(chatRoomId)
        val user = findUser(userId)
        chatRoom.addUser(user);
    }

    //채팅방 나감
    @Transactional
    fun leaveChatRoom(chatRoomId: Long,userId: Long) {
        val chatRoom = findChatRoom(chatRoomId)
        val user = findUser(userId)
        if (userId == chatRoom.creatorId) {
            throw ChatRoomException(ChatRoomExceptionMessage.REQUIRED_CHATROOM_DESCRIPTION)
        }
        chatRoom.removeUser(user);
    }

    fun findChatRoom(chatRoomId: Long): ChatRoom =
        chatRoomRepository.findById(chatRoomId)
            .orElseThrow { ChatRoomException(ChatRoomExceptionMessage.NOT_FOUND_CHATROOM) }

    fun findUser(userId: Long): User =
        userRepository.findById(userId)
            .orElseThrow{ UserException(UserExceptionMessage.NOT_FOUND_USER) }

    fun checkPermission(chatRoomId: Long, userId: Long): Boolean {
        val user = findUser(userId)
        val chatRoom = findChatRoom(chatRoomId)
        if(user.id != chatRoom.creatorId){
            throw ChatRoomException(ChatRoomExceptionMessage.NO_PERMISSION)
        }
        return true
    }

    fun ChatRoom.toResponse(): ChatRoomResponse =
        ChatRoomResponse(
            id = this.id!!,
            chatRoomName = this.chatRoomName,
            description = this.description,
            maxMembers = this.maxMembers,
            chatRoomType = this.chatRoomType,
            participation = this.participation,
            userCount = this.users.size,
            creatorId = this.creatorId
        )
}
