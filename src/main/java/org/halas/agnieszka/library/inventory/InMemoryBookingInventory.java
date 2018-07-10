package org.halas.agnieszka.library.inventory;

import org.halas.agnieszka.library.data.Book;
import org.halas.agnieszka.library.data.Booking;
import org.halas.agnieszka.library.data.CategoryBook;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Repository
public class InMemoryBookingInventory implements BookingInventory{

    private List<Booking> bookings = new ArrayList<>();

    public List<Booking> getBookings() {
        return bookings;
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    public void removeBook(Booking booking) {
        bookings.remove(booking);
    }

    public LocalDate calculateReturnDate(LocalDate returnDate) {

        LocalDate oneMonthLaterDate = returnDate.plusMonths(1);
        return oneMonthLaterDate;
    }

    public Optional<Booking> getById(int bookingId) {
        return Optional.ofNullable(bookings.get(bookingId));
    }

    public Collection<Booking> findBookingForUser(int userId) {

        Predicate<Booking> predicate = b -> b.getUser().getId() == userId;
        Collection<Booking> userCollection = bookings.stream().filter(predicate).collect(Collectors.toList());
        return userCollection;
    }

    public Optional<Booking> findBookingForBook(Book book) {

        Predicate<Booking> predicate = b -> b.getBook().equals(book);
        Optional<Booking> optional = bookings.stream().filter(predicate).findAny();
        return optional;
    }

}
