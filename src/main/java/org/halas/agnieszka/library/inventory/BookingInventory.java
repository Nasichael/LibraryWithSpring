package org.halas.agnieszka.library.inventory;

import org.halas.agnieszka.library.data.Book;
import org.halas.agnieszka.library.data.Booking;
import org.halas.agnieszka.library.data.User;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Repository
public class BookingInventory {

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

    public LocalDate calculateReturnDate(LocalDate date) {

        LocalDate oneMonthLaterDate = date.plusMonths(1);
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