package com.springBootApp.bookJournal.repository

import com.springBootApp.bookJournal.service.model.Book
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Component

interface BookRepository: MongoRepository<Book, String>{}
