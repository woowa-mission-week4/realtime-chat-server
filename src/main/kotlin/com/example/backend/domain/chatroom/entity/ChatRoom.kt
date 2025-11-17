package com.example.backend.domain.chatroom.entity

import com.example.backend.domain.chatroom.entity.enums.ChatRoomType
import com.example.backend.domain.chatroom.exception.ChatRoomException
import com.example.backend.domain.chatroom.exception.message.ChatRoomExceptionMessage
import com.example.backend.domain.user.entity.User
import com.example.backend.global.entity.BaseEntity
import jakarta.persistence.*
import org.springframework.data.jpa.domain.support.AuditingEntityListener
@Entity
@Table(name = "chat_room")
@EntityListeners(AuditingEntityListener::class)
class ChatRoom(

    chatRoomName: String,
    description: String,
    maxMembers: Int = 2, // 기본값 개인 톡방
    var chatRoomType: ChatRoomType,
    var participation: Boolean = false // 채팅방 참가 여부

) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "chat_room_user",
        joinColumns = [JoinColumn(name = "chat_room_id")],
        inverseJoinColumns = [JoinColumn(name = "user_id")]
    )
    var users: MutableList<User> = mutableListOf()

    var chatRoomName: String
    var description: String
    var maxMembers: Int

    init {
        require(chatRoomName.isNotBlank()) {
            throw ChatRoomException(ChatRoomExceptionMessage.REQUIRED_CHATROOM_NAME);
        }
        require(description.isNotBlank()) {
            throw ChatRoomException(ChatRoomExceptionMessage.REQUIRED_CHATROOM_DESCRIPTION);
        }

        // trim 처리
        val trimmedName = chatRoomName.trim()
        val trimmedDescription = description.trim()

        // 길이 체크
        require(trimmedName.length in CHATROOM_NAME_MIN_LENGTH..CHATROOM_NAME_MAX_LENGTH) {
            throw ChatRoomException(ChatRoomExceptionMessage.INVALID_CHATROOM_NAME);
        }
        require(trimmedDescription.length <= CHATROOM_DESCRIPTION_MAX_LENGTH) {
            throw ChatRoomException(ChatRoomExceptionMessage.INVALID_CHATROOM_DESCRIPTION);
        }

        // 인원 체크
        require(maxMembers in CHATROOM_MIN_PARTICIPANTS..CHATROOM_MAX_PARTICIPANTS) {
            throw ChatRoomException(ChatRoomExceptionMessage.INVALID_CHATROOM_PARTICIPANTS);
        }

        // 필드에 값 할당
        this.chatRoomName = trimmedName
        this.description = trimmedDescription
        this.maxMembers = maxMembers
    }
    // 사용자 추가
    fun addUser(user : User) {
        if (users.contains(user)) {
            throw ChatRoomException(ChatRoomExceptionMessage.ALREADY_JOINED)
        }
        if (users.size >= maxMembers) {
            throw ChatRoomException(ChatRoomExceptionMessage.INVALID_CHATROOM_PARTICIPANTS);
        }
        users.add(user)
    }
    //사용자 제거
    fun removeUser(user : User) {
        if (!users.contains(user)) {
            throw ChatRoomException(ChatRoomExceptionMessage.NOT_JOINED)
        }
        users.remove(user)
    }


    //이름 수정
    fun updateName(newName: String) {
        val trim = newName.trim()
        require(trim.isNotBlank()) {
            throw ChatRoomException(ChatRoomExceptionMessage.REQUIRED_CHATROOM_NAME);
        }
        require(trim.length in CHATROOM_NAME_MIN_LENGTH..CHATROOM_NAME_MAX_LENGTH) {
            throw ChatRoomException(ChatRoomExceptionMessage.INVALID_CHATROOM_NAME)
        }
        this.chatRoomName = trim
    }

    //설명 수정
    fun updateDescription(newDescription: String) {
        val trim = newDescription.trim()
        require(trim.isNotBlank()) {
            throw ChatRoomException(ChatRoomExceptionMessage.REQUIRED_CHATROOM_DESCRIPTION);
        }
        require(trim.length <= CHATROOM_DESCRIPTION_MAX_LENGTH) {
            throw ChatRoomException(ChatRoomExceptionMessage.INVALID_CHATROOM_DESCRIPTION)
        }
        this.description = trim
    }

    companion object {
        private const val CHATROOM_NAME_MIN_LENGTH = 2
        private const val CHATROOM_NAME_MAX_LENGTH = 20
        private const val CHATROOM_DESCRIPTION_MAX_LENGTH = 150
        private const val CHATROOM_MIN_PARTICIPANTS = 2
        private const val CHATROOM_MAX_PARTICIPANTS = 100
    }

    // JPA 기본 생성자
    constructor() : this("", "", 2, ChatRoomType.PERSONAL)
}
