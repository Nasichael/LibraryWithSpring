package org.halas.agnieszka.library;

import org.h2.tools.Server;
import org.halas.agnieszka.library.data.Book;
import org.halas.agnieszka.library.data.CategoryBook;
import org.halas.agnieszka.library.data.User;
import org.halas.agnieszka.library.inventory.db.BookRepository;
import org.halas.agnieszka.library.inventory.BookingInventory;
import org.halas.agnieszka.library.inventory.db.UserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.sql.SQLException;
import java.util.List;

@SpringBootApplication
public class LibraryApplication {

    BookRepository bookRepository;
    UserRepository userRepository;

    public LibraryApplication(BookRepository bookRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(LibraryApplication.class, args);
    }

    @Bean
    ApplicationRunner run(BookRepository bookRepository, UserRepository userRepository) {
        return args -> {

            final Book newBook = new Book("a", (short) 333, CategoryBook.SCIENCEFICTION, "aaa", 4);
            bookRepository.save(newBook);
            System.out.println(newBook);
            final User newUser = new User ("Tom","Pratt");
            userRepository.save(newUser);
        };
    }


    // get all books
    public List<Book> findAllBooks() {
        final List<Book> all = bookRepository.findAll();
        return all;
    }

    public boolean checkIfBookIdExist(int bookId) {
        final boolean check = bookRepository.existsById(bookId);
        return check;
    }

    public long countBooks() {
        final long count = bookRepository.count();
        return count;
    }


    public Book getBook(int bookId) {
        final Book book = bookRepository.findById(bookId).get();
        return book;
    }

    @Bean
    ApplicationRunner writeSomething() {
        return args -> System.out.println("something");
    }

    public void saveBook(Book book) {
        bookRepository.save(book);
    }

   @Bean
    public  Server getWebH2Server() throws SQLException {
        final Server webServer = Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082");
        webServer.start();
        return webServer;
    }


}
