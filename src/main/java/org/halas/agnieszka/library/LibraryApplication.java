package org.halas.agnieszka.library;

import org.halas.agnieszka.library.data.Book;
import org.halas.agnieszka.library.data.CategoryBook;
import org.halas.agnieszka.library.inventory.BookRepository;
import org.halas.agnieszka.library.inventory.BookingInventory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class LibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryApplication.class, args);
    }

    @Bean
    ApplicationRunner run(BookRepository bookRepository, BookingInventory bookingInventory) {
        return new ApplicationRunner() {
            @Override
            public void run(ApplicationArguments args) throws Exception {
                bookingInventory.getBookings();
                testmethod(bookRepository);
            }
        };
    }

    public void testmethod(BookRepository bookRepository) {
        //save book
        final Book newBook = new Book("a", (short) 333, CategoryBook.SCIENCEFICTION, "aaa", 4);
        bookRepository.save(newBook);
        System.out.println(newBook);
    }

    // get all books
    public List<Book> findAllBooks(BookRepository bookRepository) {
        final List<Book> all = bookRepository.findAll();
        return all;
    }

    public boolean checkIfBookIdExist(BookRepository bookRepository, int bookId) {
        final boolean check = bookRepository.existsById(bookId);
        return check;
    }

    public long countBooks(BookRepository bookRepository) {
        final long count = bookRepository.count();
        return count;
    }

    public Book getBook(BookRepository bookRepository, int bookId){
        final Book book = bookRepository.getOne(bookId);
        return book;
    }


    @Bean
    ApplicationRunner writeSomething() {
        return args -> System.out.println("something");
    }
}
