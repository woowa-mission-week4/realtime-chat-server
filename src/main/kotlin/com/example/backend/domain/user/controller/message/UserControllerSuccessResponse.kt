package com.example.backend.domain.user.controller.message

import com.example.backend.global.config.ResponseMessage
import com.example.backend.global.exception.ExceptionMessage

enum class UserControllerSuccessResponse(
    override val message: String
) : ResponseMessage {

    SIGNUP_SUCCESS("회원가입이 완료되었습니다"),
    LOGIN_SUCCESS("로그인이 완료되었습니다"),
    LOAD_MY_PROFILE_SUCCESS("나의 정보 불러오기에 성공하였습니다"),
    UPDATE_MY_PROFILE_SUCCESS("나의 정보 수정에 성공하였습니다"),
    CHANGE_MY_STATUS("사용자의 상태 변경에 성공하였습니다"),
    LOAD_ONLINE_USERS("온라인 상태의 사용자 조회를 성공하였습니다"),
    LOAD_ALL_USERS("모든 사용자 조회에 성공하였습니다")
}
