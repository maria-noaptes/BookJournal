package com.springBootApp.bookJournal.model

import com.springBootApp.bookJournal.service.model.Book
import com.springBootApp.bookJournal.service.model.Status
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

data class BookDTO(
        var id: String = "",
        val title: String = "",
        val author: String = "",
        var status: StatusDTO = StatusDTO.STARTED,
        val startDate: String = LocalDateTime.now().dayOfMonth.toString()+"."+ LocalDateTime.now().month.value+"."+ LocalDateTime.now().year,
        var finishDate: String = "not set",
        var review: String=""
) {
    @JvmOverloads
    constructor(bookDTO: BookDTO) : this() {
        var id: String = bookDTO.id
        val title: String = bookDTO.title
        val author: String = bookDTO.author
        val status: StatusDTO = bookDTO.status
        val startDate: String = bookDTO.startDate
        val finishDate: String = bookDTO.finishDate
        var review: String=bookDTO.review
    }

    fun toBook(): Book =
            Book(
                    id = id,
                    title = title,
                    author = author,
                    status = status.toStatus(),
                    startDate = startDate,
                    finishDate = finishDate,
                    review = review
            )

    companion object {
        fun from(book: Book): BookDTO =
                BookDTO(
                        id = book.id,
                        title = book.title,
                        author = book.author,
                        status = StatusDTO.from(book.status),
                        startDate = book.startDate,
                        finishDate = book.finishDate,
                        review = book.review
                )
    }
}

enum class StatusDTO {
    STARTED,
    IN_PROGRESS,
    ABANDONED,
    FINISHED;

    fun toStatus(): Status =
            Status.valueOf(this.name)

    companion object {
        fun from(status: Status): StatusDTO =
                StatusDTO.valueOf(status.name)
    }
}
