package org.halas.agnieszka.library;

import org.halas.agnieszka.library.data.Book;
import org.halas.agnieszka.library.data.CategoryBook;
import org.halas.agnieszka.library.data.User;
import org.halas.agnieszka.library.inventory.BookInventory;
import org.halas.agnieszka.library.inventory.UserInventory;
import org.halas.agnieszka.library.inventory.db.BookRepository;
import org.halas.agnieszka.library.inventory.db.UserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.h2.tools.Server;
import java.sql.SQLException;

@SpringBootApplication
public class LibraryApplication {


    public static void main(String[] args) {
        SpringApplication.run(LibraryApplication.class, args);

    }

    @Bean()
    ApplicationRunner run(BookInventory bookInventory, UserInventory userInventory) {
        return args -> {
            bookInventory.save(new Book("a", (short) 333, CategoryBook.SCIENCEFICTION, "aaa", 46));
            userInventory.save(new User("Bob", "test"));
            userInventory.save(new User("Ted", "test"));
            bookInventory.save(new Book("bbba", (short) 333, CategoryBook.SCIENCEFICTION, "aaa", 8));
        };
    }

    @Bean
    ApplicationRunner writeSomething() {
        return args -> System.out.println("something");
    }


    @Bean
    public Server getWebH2Server() throws SQLException {
        final Server webServer = Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082");
        webServer.start();
        return webServer;
    }


}
