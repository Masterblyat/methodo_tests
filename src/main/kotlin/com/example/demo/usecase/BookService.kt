package com.example.demo.usecase

import com.example.demo.model.Book
import com.example.demo.port.BookRepository

class BookService(private val bookRepository: BookRepository) {

    fun addBook(title: String, author: String): Book {
        if (title.isBlank()) {
            throw IllegalArgumentException("Title cannot be empty")
        }
        if (author.isBlank()) {
            throw IllegalArgumentException("Author cannot be empty")
        }

        val book = Book(title, author)
        bookRepository.save(book)
        return book
    }

    fun listBooks(): List<Book> {
        return bookRepository.listBooks().sortedBy { it.title }
    }
}
