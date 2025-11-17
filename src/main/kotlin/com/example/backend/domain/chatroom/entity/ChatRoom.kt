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
    maxMembers: Int = 2,
    var chatRoomType: ChatRoomType,
    var participation: Boolean = false,
    var creatorId: Long? = null

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

    var chatRoomName: String = chatRoomName
    var description: String = description
    var maxMembers: Int = maxMembers

    init {

        val isJpaLoading = this.chatRoomName.isBlank() && this.description.isBlank()

        if (!isJpaLoading) {
            validate(chatRoomName, description, maxMembers)
        }

        // trim 적용 (JPA 로딩일 때는 빈 문자열 유지)
        this.chatRoomName = this.chatRoomName.trim()
        this.description = this.description.trim()
    }

    private fun validate(name: String, desc: String, members: Int) {
        require(name.isNotBlank()) {
            throw ChatRoomException(ChatRoomExceptionMessage.REQUIRED_CHATROOM_NAME)
        }
        require(desc.isNotBlank()) {
            throw ChatRoomException(ChatRoomExceptionMessage.REQUIRED_CHATROOM_DESCRIPTION)
        }

        require(name.length in CHATROOM_NAME_MIN_LENGTH..CHATROOM_NAME_MAX_LENGTH) {
            throw ChatRoomException(ChatRoomExceptionMessage.INVALID_CHATROOM_NAME)
        }
        require(desc.length <= CHATROOM_DESCRIPTION_MAX_LENGTH) {
            throw ChatRoomException(ChatRoomExceptionMessage.INVALID_CHATROOM_DESCRIPTION)
        }

        require(members in CHATROOM_MIN_PARTICIPANTS..CHATROOM_MAX_PARTICIPANTS) {
            throw ChatRoomException(ChatRoomExceptionMessage.INVALID_CHATROOM_PARTICIPANTS)
        }
    }

    // 사용자 추가
    fun addUser(user: User) {
        if (users.contains(user)) throw ChatRoomException(ChatRoomExceptionMessage.ALREADY_JOINED)
        if (users.size >= maxMembers) throw ChatRoomException(ChatRoomExceptionMessage.INVALID_CHATROOM_PARTICIPANTS)
        users.add(user)
    }

    // 사용자 제거
    fun removeUser(user: User) {
        if (!users.contains(user)) throw ChatRoomException(ChatRoomExceptionMessage.NOT_JOINED)
        users.remove(user)
    }

    // 이름 수정
    fun updateName(newName: String) {
        val n = newName.trim()
        require(n.isNotBlank()) { throw ChatRoomException(ChatRoomExceptionMessage.REQUIRED_CHATROOM_NAME) }
        require(n.length in CHATROOM_NAME_MIN_LENGTH..CHATROOM_NAME_MAX_LENGTH) {
            throw ChatRoomException(ChatRoomExceptionMessage.INVALID_CHATROOM_NAME)
        }
        this.chatRoomName = n
    }

    // 설명 수정
    fun updateDescription(newDescription: String) {
        val d = newDescription.trim()
        require(d.isNotBlank()) { throw ChatRoomException(ChatRoomExceptionMessage.REQUIRED_CHATROOM_DESCRIPTION) }
        require(d.length <= CHATROOM_DESCRIPTION_MAX_LENGTH) {
            throw ChatRoomException(ChatRoomExceptionMessage.INVALID_CHATROOM_DESCRIPTION)
        }
        this.description = d
    }

    companion object {
        private const val CHATROOM_NAME_MIN_LENGTH = 2
        private const val CHATROOM_NAME_MAX_LENGTH = 20
        private const val CHATROOM_DESCRIPTION_MAX_LENGTH = 150
        private const val CHATROOM_MIN_PARTICIPANTS = 2
        private const val CHATROOM_MAX_PARTICIPANTS = 100
    }

    /**
     * JPA 기본 생성자
     * Hibernate가 사용할 때는 "", "", 2 를 넘기므로 검증 스킵됨
     */
    protected constructor() : this(
        chatRoomName = "",
        description = "",
        maxMembers = 2,
        chatRoomType = ChatRoomType.PERSONAL
    )
}
