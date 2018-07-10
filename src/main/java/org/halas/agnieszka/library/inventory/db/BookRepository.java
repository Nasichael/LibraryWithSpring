package org.halas.agnieszka.library.inventory.db;

import org.halas.agnieszka.library.data.Book;
import org.halas.agnieszka.library.inventory.BookInventory;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Profile("db")
@Repository
public interface BookRepository extends JpaRepository<Book, Integer> , BookInventory{
}
