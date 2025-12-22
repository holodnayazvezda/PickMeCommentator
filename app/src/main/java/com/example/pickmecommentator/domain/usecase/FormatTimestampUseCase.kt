package com.example.pickmecommentator.domain.usecase

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class FormatTimestampUseCase {
    operator fun invoke(timestamp: Long): String {
        val sdf = SimpleDateFormat("HH:mm:ss dd.MM.yy", Locale.getDefault())
        val date = Date(timestamp * 1000)
        return sdf.format(date)
    }
}
