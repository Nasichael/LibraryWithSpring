package org.halas.agnieszka.library;

import org.halas.agnieszka.library.data.Book;
import org.halas.agnieszka.library.data.Booking;
import org.halas.agnieszka.library.data.SearchBookView;
import org.halas.agnieszka.library.data.User;
import org.halas.agnieszka.library.engine.Filters;
import org.halas.agnieszka.library.engine.Library;
import org.halas.agnieszka.library.inventory.BookInventory;
import org.halas.agnieszka.library.inventory.InMemoryBookInventory;
import org.halas.agnieszka.library.inventory.BookingInventory;
import org.halas.agnieszka.library.inventory.UserInventory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
public class LibraryController {

    BookInventory bookInventory;
    UserInventory userInventory;
    BookingInventory bookingInventory;
    Library library;


    public LibraryController(BookInventory bookInventory, UserInventory userInventory, Library library, BookingInventory bookingInventory) {
        this.bookInventory = bookInventory;
        this.userInventory = userInventory;
        this.library = library;
        this.bookingInventory = bookingInventory;

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

    @GetMapping("search/title/{word}")
    public List<SearchBookView> serachViews(@PathVariable("word") String word) {
        final List<SearchBookView> searchView = library.searchBookView(Filters.title(word));
        return searchView;
    }

    @GetMapping("search/id/{bookId}")
    public List<SearchBookView> serachViews1(@PathVariable("bookId") int bookId) {
        final List<SearchBookView> view1 = library.searchBookView(Filters.ID(bookId));
        return view1;
    }
}
