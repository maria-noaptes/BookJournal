package com.springBootApp.bookJournal.controller

import com.springBootApp.bookJournal.model.BookDTO
import com.springBootApp.bookJournal.model.BookDTO.Companion.from
import com.springBootApp.bookJournal.model.StatusDTO
import com.springBootApp.bookJournal.service.BookService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("/books")
class BookController(
        private val bookService: BookService
) {

    @GetMapping
    fun getBooks(): List<BookDTO> =
            bookService.retrieveBooks()
                    .map { BookDTO.from(it) }

    @GetMapping("/{id}")
    fun getBook(
            @PathVariable id: String
    ): ResponseEntity<BookDTO> =
            try {
                val book = bookService.getBook(id)
                ResponseEntity.ok(BookDTO.from(book!!))
            } catch (e: Exception) {
                ResponseEntity.notFound().build()
            }

    @PostMapping
    fun addBook(
            @RequestBody bookDTO: BookDTO
    ): ResponseEntity<BookDTO> =
            try {
                bookDTO.id = bookService.generateId()
                val book = bookService.addBook(bookDTO.toBook())
                ResponseEntity.ok(from(book))
            } catch (e: IllegalArgumentException) {
                ResponseEntity.status(HttpStatus.CONFLICT).build()
            }

    @PutMapping("/{id}")
    fun updateBook(
            @PathVariable id: String,
            @RequestBody bookDTO: BookDTO
    ): ResponseEntity<BookDTO> =
            try {
                val id=id
                val status=bookDTO.status
                val time = LocalDateTime.now().dayOfMonth.toString() + "." + LocalDateTime.now().month.value + "." + LocalDateTime.now().year
                val bookDTO = BookDTO(from(bookService.getBook(id)!!))
                bookDTO.status=status
                if(status==StatusDTO.FINISHED)
                    bookDTO.finishDate=time
                val book = bookService.updateBook(bookDTO.toBook())
                ResponseEntity.ok(BookDTO.from(book))
            } catch (e: Exception) {
                ResponseEntity.notFound().build()
            }

    @DeleteMapping("/{id}")
    fun deleteBook(
            @PathVariable id: String
    ): ResponseEntity<String> =
            try {
                if(bookService.deleteBook(id))
                ResponseEntity<String>("deleted",HttpStatus.OK)
                else ResponseEntity<String>("id not found",HttpStatus.NOT_FOUND)
            } catch (e: Exception) {
                ResponseEntity<String>("not found",HttpStatus.NOT_FOUND)
}}