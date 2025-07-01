package com.example.demo.model

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.string.shouldNotBeBlank
import io.kotest.matchers.string.shouldNotBeEmpty

class BookTest : DescribeSpec({

    describe("Book model validation") {

        describe("Book creation") {
            it("should create a book with non-null title and author") {
                // Given
                val title = "The Great Gatsby"
                val author = "F. Scott Fitzgerald"

                // When
                val book = Book(title, author)

                // Then
                book.title shouldBe title
                book.author shouldBe author
            }

        }

        describe("Book equality and data class behavior") {
            it("should be equal when title and author are the same") {
                // Given
                val book1 = Book("Pride and Prejudice", "Jane Austen")
                val book2 = Book("Pride and Prejudice", "Jane Austen")

                // When & Then
                book1 shouldBe book2
            }

            it("should not be equal when title or author differs") {
                // Given
                val book1 = Book("Harry Potter", "J.K. Rowling")
                val book2 = Book("Harry Potter", "Different Author")
                val book3 = Book("Different Title", "J.K. Rowling")

                // When & Then
                book1 shouldNotBe book2
                book1 shouldNotBe book3
                book2 shouldNotBe book3
            }
        }

        describe("Book properties access") {
            it("should allow access to title property") {
                // Given
                val book = Book("The Catcher in the Rye", "J.D. Salinger")

                // When & Then
                book.title shouldBe "The Catcher in the Rye"
            }

            it("should allow access to author property") {
                // Given
                val book = Book("Lord of the Flies", "William Golding")

                // When & Then
                book.author shouldBe "William Golding"
            }
        }
    }
})
