package com.example.demo.port

import com.example.demo.model.Book

interface BookRepository {

    fun save(book: Book)

    fun listBooks(): List<Book>
}