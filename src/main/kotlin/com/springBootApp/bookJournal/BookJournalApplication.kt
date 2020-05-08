package com.springBootApp.bookJournal

import com.springBootApp.bookJournal.service.model.Book
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication//(exclude = [MongoAutoConfiguration::class/*,MongoDataAutoConfiguration::class*/])
class BookJournalApplication
@Suppress("SpreadOperator")
fun main(args: Array<String>) {
	runApplication<BookJournalApplication>(*args)
}
