package org.halas.agnieszka.library;

import org.halas.agnieszka.library.data.*;
import org.halas.agnieszka.library.engine.Filters;
import org.halas.agnieszka.library.engine.Library;
import org.halas.agnieszka.library.inventory.*;
import org.junit.Test;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class LibraryTest {

    InMemoryBookInventory inMemoryBookInventory = new InMemoryBookInventory();
    InMemoryBookingInventory inMemoryBookingInventory = new InMemoryBookingInventory();
    InMemoryUserInventory inMemoryUserInventory = new InMemoryUserInventory();

    Library library = new Library(inMemoryBookInventory, inMemoryBookingInventory, inMemoryUserInventory);

    @Test
    public void shouldSearchForExistingTitle() {

        //given
        String title = "Ania";

        //when
        final List<Book> bookList = library.search(Filters.title(title));

        //then
        assertEquals(2, bookList.size());
    }

    @Test
    public void shouldSearchCategoryAndTitle() {

        //given
        CategoryBook categoryBook = CategoryBook.SCIENCEFICTION;
        String title = "Sol";

        //when
        final List<Book> bookList = library.search(Filters.category(categoryBook), Filters.title(title));

        //then
        assertEquals(2, bookList.size());
    }

    @Test
    public void shouldSearchByManyFilters() {

        //given
        String title = "Avon";
        String author = "Lucy";
        //CategoryBook categoryBook = CategoryBook.NOVEL;

        //when
        final List<Book> bookList = library.search(Filters.category(CategoryBook.NOVEL),
                Filters.title(title), Filters.author(author), Filters.category(CategoryBook.NOVEL));

        //then
        assertEquals(1, bookList.size());
    }

    @Test
    public void shouldSearchByID() {

        //given
        int ID = 4;

        //when
        final List<Book> bookList = library.search(Filters.ID(ID));

        //then
        assertEquals(1, bookList.size());
    }

    @Test
    public void shouldSearchByYear() {

        //given
        short year = 2017;

        //when
        List<Book> bookList = library.search(Filters.year(year));

        //then
        assertEquals(1, bookList.size());

    }

    @Test
    public void shouldRentOneBook() {

        //given
        Optional<Book> book = inMemoryBookInventory.getById(5);
        Optional<User> user = inMemoryUserInventory.getById(3);

        //when
        final Booking rent = library.rent(book.get().getId(), user.get().getId());

        //then
        Collection<Booking> bookings = library.getRentedBooksForUser(user.get().getId());
        assertEquals(1, bookings.size());
    }

    @Test
    public void shouldRentTwoBooks() {

        //given
        Optional<Book> book = inMemoryBookInventory.getById(8);
        Optional<Book> book2 = inMemoryBookInventory.getById(7);
        Optional<User> user = inMemoryUserInventory.getById(1);

        //when
        final Booking rent = library.rent(book2.get().getId(), user.get().getId());
        final Booking rent2 = library.rent(book.get().getId(), user.get().getId());

        //then
        //rent.getUser()==user
        //rent.getBook() ==book
       /* assertEquals(book, rent.getBook());
        assertEquals(user, rent.getUser());*/

        Collection<Booking> bookings = library.getRentedBooksForUser(user.get().getId());
        assertEquals(2, bookings.size());
    }

    @Test
    public void shouldReturnBook() {

        //given
        Optional<Book> book = inMemoryBookInventory.getById(3);
        Optional<Book> book2 = inMemoryBookInventory.getById(6);
        Optional<Book> book3 = inMemoryBookInventory.getById(5);
        Optional<User> user = inMemoryUserInventory.getById(7);
        Optional<User> user2 = inMemoryUserInventory.getById(2);
        Booking booking1 = library.rent(book.get().getId(), user.get().getId());
        Booking booking2 = library.rent(book2.get().getId(), user.get().getId());
        Booking booking3 = library.rent(book3.get().getId(), user2.get().getId());

        //when
        library.returnBook(booking2);

        //then
        assertEquals(2, inMemoryBookingInventory.getBookings().size());

        Collection<Booking> bookings = library.getRentedBooksForUser(user.get().getId());
        assertEquals(1, bookings.size());
    }

    @Test
    public void shouldCheckIfBookNumberReachedLimit() {
        //given
        Optional<User> user = inMemoryUserInventory.getById(7);
        Optional<Book> book = inMemoryBookInventory.getById(3);
        Optional<Book> book2 = inMemoryBookInventory.getById(6);
        library.rent(book.get().getId(), user.get().getId());
        library.rent(book2.get().getId(), user.get().getId());

        //when
        final boolean checkBookLimit = library.checkBookLimit(user.get().getId());
        //then
        assertEquals(false, checkBookLimit);

    }

    @Test
    public void shouldCheckIfBookNumberNotReachedLimit() {
        //given
        Optional<User> user = inMemoryUserInventory.getById(7);
        Optional<Book> book = inMemoryBookInventory.getById(3);
        Optional<Book> book2 = inMemoryBookInventory.getById(6);
        Optional<Book> book3 = inMemoryBookInventory.getById(1);
        library.rent(book.get().getId(), user.get().getId());
        library.rent(book2.get().getId(), user.get().getId());
        library.rent(book3.get().getId(), user.get().getId());

        //when
        final boolean checkBookLimit = library.checkBookLimit(user.get().getId());
        //then
        assertEquals(true, checkBookLimit);

    }

    @Test
    public void shouldCheckIfBookRented() {
        //given
        Optional<User> user = inMemoryUserInventory.getById(7);
        Optional<Book> book = inMemoryBookInventory.getById(3);
        library.rent(book.get().getId(), user.get().getId());

        //when
        final boolean checkRentedBook = library.checkIfBookRented(book.get());

        //then
        assertEquals(true, checkRentedBook);
    }

    @Test
    public void shouldCheckIfNotBookRented() {
        //given
        Optional<Book> book = inMemoryBookInventory.getById(3);

        //when
        final boolean checkRentedBook = library.checkIfBookRented(book.get());

        //then
        assertEquals(false, checkRentedBook);
    }

    @Test
    public void shouldCreateSearchBookView() {
        //given
        Optional<Book> book = inMemoryBookInventory.getById(13);
        Optional<User> user = inMemoryUserInventory.getById(4);

        //when
        library.rent(book.get().getId(), user.get().getId());
        List<SearchBookView> searchBookView = library.searchBookView(Filters.title("an"));

        //then
        assertEquals(3, searchBookView.size());
    }

    @Test
    public void shouldCalculateReturnDate() {

        //given
        Optional<Book> book = inMemoryBookInventory.getById(12);
        Optional<User> user = inMemoryUserInventory.getById(6);
        library.rent(book.get().getId(), user.get().getId());
        Optional<Booking> booking = inMemoryBookingInventory.getById(0);

        //when
        LocalDate returnDate = inMemoryBookingInventory.calculateReturnDate(booking.get().getDate());

        //then
        assertEquals(LocalDate.now().plusMonths(1), returnDate);
    }

    @Test
    public void shouldNotFindBook() {

        //given
        Optional<User> user = inMemoryUserInventory.getById(2);
        final int bookId = 23;

        try {
            //when
            Booking booking = library.rent(bookId, user.get().getId());
        } catch (IllegalArgumentException e) {

            //then
            assertEquals("book with the id: " + bookId + " is unavailable", e.getMessage());
        }
    }

    @Test
    public void shouldNotFindUser() {
        //given
        Optional<Book> book = inMemoryBookInventory.getById(2);
        final int userId = 23;
        try {
            //when
            Booking booking = library.rent(book.get().getId(), 23);
        } catch (IllegalArgumentException e) {
            //then
            assertEquals("user with the id: " + userId + " is not valid", e.getMessage());
        }
    }
}




