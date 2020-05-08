package com.springBootApp.bookJournal.service

import com.springBootApp.bookJournal.repository.BookRepository
import com.springBootApp.bookJournal.service.model.Book
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service

@Service
@Component
class BookService (@Autowired
    private val bookRepository: BookRepository)
{
    private val logger = KotlinLogging.logger {}

    fun addBook(book: Book): Book {
       return bookRepository.save(book)
    }
    fun updateBook(book: Book): Book {
        return bookRepository.save(book)
    }
    fun deleteBook(id: String): Boolean{
        try {
            bookRepository.deleteById(id)
        }catch(e: Exception)
        {
            return false
        }
        return true
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
