package org.halas.agnieszka.library.inventory;

import org.halas.agnieszka.library.data.Book;
import org.halas.agnieszka.library.data.Booking;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface BookingInventory {

    List<Booking> getBookings();

    void addBooking(Booking booking);

    void removeBook(Booking booking);

    LocalDate calculateReturnDate(LocalDate returnDate);

    Optional<Booking> getById(int bookingId);

    Collection<Booking> findBookingForUser(int userId);

    Optional<Booking> findBookingForBook(Book book);
}