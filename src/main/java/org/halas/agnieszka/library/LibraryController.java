package org.halas.agnieszka.library;

import org.halas.agnieszka.library.data.Book;
import org.halas.agnieszka.library.data.Booking;
import org.halas.agnieszka.library.data.SearchBookView;
import org.halas.agnieszka.library.data.User;
import org.halas.agnieszka.library.engine.Filters;
import org.halas.agnieszka.library.engine.Library;
import org.halas.agnieszka.library.inventory.*;
import org.halas.agnieszka.library.inventory.db.BookRepository;
import org.halas.agnieszka.library.inventory.db.BookingRepository;
import org.halas.agnieszka.library.inventory.db.UserRepository;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
public class LibraryController {

    BookInventory bookInventory;
    UserInventory userInventory;
    BookingInventory bookingInventory;
    Library library;
    LibraryApplication libraryApplication;

    BookRepository bookRepository;
    BookingRepository bookingRepository;
    UserRepository userRepository;


    public LibraryController(BookInventory bookInventory, UserInventory userInventory, Library library,
                             BookingInventory bookingInventory, LibraryApplication libraryApplication,
                             BookRepository bookRepository, UserRepository userRepository,
                             BookingRepository bookingRepository) {
        this.bookInventory = bookInventory;
        this.userInventory = userInventory;
        this.bookingInventory = bookingInventory;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.library = library;
        this.libraryApplication = libraryApplication;
        this.bookingRepository = bookingRepository;
    }


    @GetMapping("booking")
    public String getBooking() {
        return "Booking";
    }

    @GetMapping("books/{bookId}")
    public Book getBook(@PathVariable("bookId") int id) {
        final Optional<Book> book = bookInventory.getById(id);
        return book.get();
    }

    @GetMapping("books/rent/{bookId}/user/{userId}")
    public Booking getrentBook(@PathVariable("bookId") int bookId, @PathVariable("userId") int userId) {
        final Booking rent = library.rent(bookId, userId);
        return rent;
    }

    @GetMapping("books")
    public Collection<Book> getBooks() {
        return bookInventory.getAll();
    }

    @GetMapping("users")
    public Collection<User> getUsers() {
        return userInventory.getAll();
    }

    ///////////////////////////////////////////////////////////

    @GetMapping("checkBookAboveLimit/{userId}")
    public boolean check(@PathVariable("userId") int user) {
        final boolean checkBook = library.checkBookLimit(user);
        return checkBook;
    }

    @GetMapping("bookings/getUserBooking/{userId}")
    public Collection<Booking> bookingsForUser(@PathVariable("userId") int userId) {
        final Collection<Booking> userBooking = bookingInventory.findBookingForUser(userId);
        return userBooking;
    }

    @GetMapping("search/title")
    public List<SearchBookView> serachViews(@RequestParam("word") String word) {
        final List<SearchBookView> searchView = library.searchBookView(Filters.title(word));
        return searchView;
    }

    @GetMapping("search/id/{bookId}")
    public List<SearchBookView> serachViews1(@PathVariable("bookId") int bookId) {
        final List<SearchBookView> view1 = library.searchBookView(Filters.ID(bookId));
        return view1;
    }

    @GetMapping("search/author/{author}")
    public List<SearchBookView> searchBookViews2(@PathVariable("author") String author) {
        final List<SearchBookView> view2 = library.searchBookView(Filters.author(author));
        return view2;
    }

    /////////////////////////////////////////////////////////////
    @GetMapping("db/books")
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("db/books/exists/{bookId}")
    public boolean checkIfBookExist(@PathVariable("bookId") int bookId) {
        return libraryApplication.checkIfBookIdExist(bookId);
    }

    @GetMapping("db/books/count")
    public long countBook() {
        return libraryApplication.countBooks();
    }

    @GetMapping("db/books/{bookId}")
    public Book book(@PathVariable("bookId") int bookId) {
        return libraryApplication.getBook(bookId);
    }

    @PostMapping("db/books/")
    public void saveBook(@RequestBody Book book) {
        libraryApplication.saveBook(book);
    }

    @DeleteMapping("books/{bookId}")
    public void deleteBook(@PathVariable("bookId") int bookId) {
        libraryApplication.deleteBook(bookId);
    }

    @DeleteMapping("books")
    public void deleteAllBooks(){
        libraryApplication.deleteAllBooks();
    }
}



