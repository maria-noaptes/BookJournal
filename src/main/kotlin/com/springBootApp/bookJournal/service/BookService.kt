package com.springBootApp.bookJournal.service

import com.springBootApp.bookJournal.model.BookDTO
import com.springBootApp.bookJournal.repository.BookRepository
import com.springBootApp.bookJournal.service.model.Book
import com.springBootApp.bookJournal.service.model.Status
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
@Component
class BookService (@Autowired
    private val bookRepository: BookRepository)
{
    private val logger = KotlinLogging.logger {}

    fun addBook(book: Book): Book {
       return bookRepository.save(book)
    }
    fun updateBook(book: Book): Book? {
        try{
            val bookFound=bookRepository.findById(book.id).get()
            if(bookFound.status==Status.valueOf("FINISHED")) {
                logger.error{ "Can't change status of a finished book" }
                return null
            }
            bookRepository.delete(bookFound)
            bookFound.status=book.status
            if(book.status == Status.valueOf("FINISHED")) bookFound.finishDate = LocalDateTime.now().dayOfMonth.toString() + "." + LocalDateTime.now().month.value + "." + LocalDateTime.now().year
            return bookRepository.save(bookFound)
        }catch(e:NoSuchElementException)
        {
            logger.error { "Book ${book.id} not found" }
            return null
        }
    }
    fun deleteBook(id: String): Boolean{
        return try {
            bookRepository.delete(bookRepository.findById(id).get())
            true
        } catch (e: NoSuchElementException) {
            logger.error(e) { "Book $id not found" }
            false
        }
    }

    fun retrieveBooks(): List<Book> = bookRepository.findAll()

    fun getBook(id: String): Book? {
        return try {
            bookRepository.findById(id).get()
        } catch (e: NoSuchElementException) {
            logger.error(e) { "Book $id not found" }
            null
        }
    }

    fun generateId()= "${bookRepository.count()}"
}
