package com.avangard.bratstvo.base.exceptions.domain

import java.io.IOException

/**
 * Исключение с базовым сообщением
 */
open class BaseException(
    cause: Throwable? = null,
    message: String? = cause?.localizedMessage,
    val code: Int? = null
) : IOException(message, cause)