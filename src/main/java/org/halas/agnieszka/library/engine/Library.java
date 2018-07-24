package org.halas.agnieszka.library.engine;

import org.halas.agnieszka.library.data.*;
import org.halas.agnieszka.library.inventory.BookInventory;
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

    BookInventory bookInventory;
    BookingInventory bookingInventory;
    UserInventory userInventory;


    public Library(BookInventory bookInventory, BookingInventory bookingInventory, UserInventory userInventory) {
        this.bookInventory = bookInventory;
        this.bookingInventory = bookingInventory;
        this.userInventory = userInventory;
    }

    public List<Book> search(Predicate<Book>... predicates) {

        final Predicate<Book> bookPredicate = Arrays.stream(predicates)
                .reduce(Predicate::and)
                .orElse(t -> true);

        List<Book> filteredBooks = bookInventory.findAll()
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
                    Optional<Booking> booking = bookingInventory.findForBook(book).stream().findAny();

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

        final Optional<User> user = userInventory.getById(userId);
        final Optional<Book> book = bookInventory.getById(bookId);

        if (!book.isPresent()) {
            throw new IllegalArgumentException("book with the id: " + bookId + " is unavailable");
        }

        if (!user.isPresent()) {
            throw new IllegalArgumentException("user with the id: " + userId + " is not valid");
        }


        Booking booking = new Booking(Booking.getNextId(), user.get(), book.get(), LocalDate.now());
        bookingInventory.addBooking(booking);
        return booking;
    }

    public Collection<Booking> getRentedBooksForUser(int userId) {
        return bookingInventory.findBookingForUser(userId);
    }

    public void returnBook(Booking booking) {
        bookingInventory.delete(booking);
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
        Optional<Booking> booking = bookingInventory.findForBook(book).stream().findAny();
        ;
        return booking.isPresent();
    }


    public boolean checkIfBookIdExist(int bookId) {
        final boolean check = bookInventory.existsById(bookId);
        return check;
    }

    public void saveBook(Book book) {
        bookInventory.save(book);
    }

    public void deleteAllBooks() {
        bookInventory.deleteAll();
    }

    public void deleteBook(int bookId) {
        bookInventory.deleteById(bookId);
    }

    public List<Book> findAll() {
        final List<Book> all = bookInventory.findAll();
        return all;
    }

    public Book getBook(int bookId) {
        final Book book = bookInventory.findById(bookId).get();
        return book;
    }

    public long countBooks() {
        final long count = bookInventory.count();
        return count;
    }
}


