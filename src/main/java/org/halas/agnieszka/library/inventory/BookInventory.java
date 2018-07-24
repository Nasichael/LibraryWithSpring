package org.halas.agnieszka.library.inventory;

import org.halas.agnieszka.library.data.Book;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface BookInventory {

    List<Book> findAll();

    Optional<Book> getById(int bookId);

    Book save(Book book);

    void deleteAll();

    void deleteById(int bookId);

    boolean existsById(int bookId);

    Optional<Book> findById(int bookId);

    long count();
}
