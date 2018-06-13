package org.halas.agnieszka.library.engine;

import org.halas.agnieszka.library.data.*;
import org.halas.agnieszka.library.inventory.InMemoryBookInventory;
import org.halas.agnieszka.library.inventory.BookingInventory;
import org.halas.agnieszka.library.inventory.UserInventory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class Library {
    public static final int BOOKING_LIMIT = 3;


    InMemoryBookInventory inMemoryBookInventory;
    BookingInventory bookingInventory;
    UserInventory userInventory;

    public Library(InMemoryBookInventory inMemoryBookInventory, BookingInventory bookingInventory1, UserInventory userInventory) {
        this.inMemoryBookInventory = inMemoryBookInventory;
        this.bookingInventory = bookingInventory1;
        this.userInventory = userInventory;
    }

    public List<Book> search(Predicate<Book>... predicates) {

        final Predicate<Book> bookPredicate = Arrays.stream(predicates)
                .reduce(Predicate::and)
                .orElse(t -> true);

        List<Book> filteredBooks = inMemoryBookInventory.getAll()
                .stream()
                .filter(bookPredicate)
                .collect(Collectors.toList());
        System.out.println(filteredBooks.size());
        return filteredBooks;
    }

    public List<SearchBookView> searchBookView(Predicate<Book>... predicates) {

        List<Book> filteredBooks = this.search(predicates);

        List<SearchBookView> filteredView =
                filteredBooks.stream().map(book -> {
                    Optional<Booking> booking = bookingInventory.findBookingForBook(book);

                    if (booking.isPresent()) {
                        return new SearchBookView(book.getId(), book.getAuthor(), book.getCategoryBook(),
                                book.getTitle(), book.getYear(), BookStatus.RENTED, bookingInventory.calculateReturnDate(booking.get().getDate()));
                    } else {
                        return new SearchBookView(book.getId(), book.getAuthor(), book.getCategoryBook(),
                                book.getTitle(), book.getYear(), BookStatus.AVAILABLE);
                    }
                }).collect(Collectors.toList());

        return filteredView;
    }

   /* public Booking rent(Book book, User user) {
        Booking booking = new Booking(Booking.getNextId(), user, book, LocalDate.now());
        bookingInventory.addBooking(booking);
        return booking;
    }*/

    public Booking rent(int bookId, int userId) {

        final Optional <User> user = userInventory.getById(userId);
        final Optional<Book> book = inMemoryBookInventory.getById(bookId);
        Booking booking = new Booking(Booking.getNextId(), user.get(), book.get(), LocalDate.now());
        bookingInventory.addBooking(booking);
        return booking;
    }

    public Collection<Booking> getRentedBooksForUser(int userId) {
        return bookingInventory.findBookingForUser(userId);
    }

    public void returnBook(Booking booking) {
        bookingInventory.removeBook(booking);
    }

    public boolean checkBookLimit(int userId) {
        int count = bookingInventory.findBookingForUser(userId).size();
        if (count >= BOOKING_LIMIT) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkIfBookRented(Book book) {
        Optional<Booking> booking = bookingInventory.findBookingForBook(book);
        return booking.isPresent();
    }
}

