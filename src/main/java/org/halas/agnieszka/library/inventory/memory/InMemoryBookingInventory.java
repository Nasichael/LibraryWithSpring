package org.halas.agnieszka.library.inventory.memory;

import org.halas.agnieszka.library.data.Book;
import org.halas.agnieszka.library.data.Booking;
import org.halas.agnieszka.library.data.CategoryBook;
import org.halas.agnieszka.library.inventory.BookingInventory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Component
@Profile("inMemory")
public class InMemoryBookingInventory implements BookingInventory {

    private List<Booking> bookings = new ArrayList<>();

    public List<Booking> getBookings() {
        return bookings;
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    public void delete(Booking booking) {
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
        Collection<Booking> userCollection = bookings.stream().filter(predicate).collect(toList());
        return userCollection;
    }

    public Collection<Booking> findForBook(Book bookById) {

        Predicate<Booking> predicate = b -> b.getBook().equals(bookById);
       return bookings.stream().filter(predicate).collect(toList());

    }

}
