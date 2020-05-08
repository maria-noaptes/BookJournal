package com.springBootApp.bookJournal.service.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document ("books")
data class Book(
        @Id var id: String = "",
        var title: String = "",
        var author: String = "",
        var status: Status = Status.STARTED,
        val startDate: String = LocalDateTime.now().dayOfMonth.toString()+"."+LocalDateTime.now().month.value+"."+LocalDateTime.now().year,
        var finishDate: String = "not set",
        var review: String=""
)
enum class Status {
    STARTED,
    IN_PROGRESS,
    ABANDONED,
    FINISHED
}