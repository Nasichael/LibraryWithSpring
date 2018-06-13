package org.halas.agnieszka.library.engine;

import org.halas.agnieszka.library.data.Book;
import org.halas.agnieszka.library.data.CategoryBook;
import org.springframework.stereotype.Service;

import java.util.function.Predicate;


public class Filters {

    public static Predicate<Book> title(String title) {
        return book -> book.getTitle().contains(title);
    }

    public static Predicate<Book> author(String author) {
        return book -> book.getAuthor().contains(author);
    }

    public static Predicate<Book> category(CategoryBook categoryBook) {
        return book -> book.getCategoryBook().equals(categoryBook);
    }
    public static Predicate<Book> year(Short year) {
        return book -> book.getYear() == year;
    }

    public static Predicate<Book> ID(int ID) {
        return book -> book.getId() == ID;
    }

}
