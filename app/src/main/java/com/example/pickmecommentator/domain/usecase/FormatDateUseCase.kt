package com.example.pickmecommentator.domain.usecase

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

class FormatDateUseCase {
    private val outputFormatter = DateTimeFormatter.ofPattern("yy.MM.dd HH:mm")

    operator fun invoke(dateString: String): String {
        return try {
            val dateTime = LocalDateTime.parse(dateString)
            dateTime.format(outputFormatter)
        } catch (e: DateTimeParseException) {
            dateString
        }
    }
}
