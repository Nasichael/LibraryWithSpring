package org.halas.agnieszka.library.inventory;

import org.halas.agnieszka.library.data.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
