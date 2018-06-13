package org.halas.agnieszka.library.inventory;

import org.halas.agnieszka.library.data.Book;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Profile("DB")
@Repository
public class DBBookInventory implements BookInventory {

    public Collection<Book> getAll() {
        return null;
    }

    public Optional<Book> getById(int bookId) {
        return null;
    }
}
