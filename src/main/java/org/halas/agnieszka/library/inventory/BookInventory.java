package org.halas.agnieszka.library.inventory;

import org.halas.agnieszka.library.data.Book;

import java.util.Collection;
import java.util.Optional;

public interface BookInventory {

    Collection<Book> getAll();

    Optional<Book> getById(int bookId);

}
