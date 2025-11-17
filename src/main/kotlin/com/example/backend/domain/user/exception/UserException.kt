package com.example.backend.domain.user.exception

import com.example.backend.global.exception.BusinessException
import com.example.backend.global.exception.ExceptionMessage

class UserException(
    exceptionMessage: ExceptionMessage
) : BusinessException(exceptionMessage)
