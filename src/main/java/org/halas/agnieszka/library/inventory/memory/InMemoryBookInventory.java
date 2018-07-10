package org.halas.agnieszka.library.inventory.memory;

import org.halas.agnieszka.library.data.Book;
import org.halas.agnieszka.library.data.CategoryBook;
import org.halas.agnieszka.library.inventory.BookInventory;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class InMemoryBookInventory implements BookInventory {

    Map<Integer, Book> books = new HashMap<>();

    public InMemoryBookInventory() {
        books.put(1, new Book("Stanisław Lem", (short) 1961, CategoryBook.SCIENCEFICTION, "Solaris", 1));
        books.put(2, new Book("Stanisław Lem", (short) 1961, CategoryBook.SCIENCEFICTION, "Solaris", 2));
        books.put(3, new Book("Stanisław Lem", (short) 1959, CategoryBook.SCIENCEFICTION, "Eden", 3));
        books.put(4, new Book("Stanisław Lem", (short) 1961, CategoryBook.SCIENCEFICTION, "Powrót z gwiazd", 4));
        books.put(5, new Book("Lucy Maud Montgomery", (short) 1908, CategoryBook.NOVEL, "Ania z Zielonego Wzgórza", 5));
        books.put(6, new Book("Lucy Maud Montgomery", (short) 1909, CategoryBook.NOVEL, "Ania z Avonlea", 6));
        books.put(7, new Book("John Ronald Reuel Tolkien", (short) 1954, CategoryBook.FANTASY, "Władca Pierścieni", 7));
        books.put(8, new Book("John Ronald Reuel Tolkien", (short) 1954, CategoryBook.FANTASY, "Władca Pierścieni", 8));
        books.put(9, new Book("John Ronald Reuel Tolkien", (short) 1954, CategoryBook.FANTASY, "Władca Pierścieni", 9));
        books.put(10, new Book("John Ronald Reuel Tolkien", (short) 1937, CategoryBook.FANTASY, "Hobbit", 10));
        books.put(11, new Book("Joshua Bloch", (short) 2017, CategoryBook.GUIDE, "Effective Java", 11));
        books.put(12, new Book("Edgar Rice Burroughs", (short) 1912, CategoryBook.ADVENTURE, "Tarzan of the Apes", 12));
        books.put(13, new Book("Edgar Rice Burroughs", (short) 1912, CategoryBook.ADVENTURE, "Tarzan of the Apes", 13));
        books.put(14, new Book("Edgar Rice Burroughs", (short) 1912, CategoryBook.ADVENTURE, "Tarzan of the Apes", 14));
        books.put(15, new Book("Jules Verne", (short) 1864, CategoryBook.SCIENCEFICTION, "Journey to the Center of the Earth", 15));
    }

    public Collection<Book> getAll() {
        return books.values();
    }

    public Optional<Book> getById(int bookId) {
        return Optional.ofNullable(books.get(bookId));
    }

    @Override
    public List<Book> findAll() {
        return null;
    }

}
