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
    BookingInventory bookingInventory = new BookingInventory();
    //InMemoryUserInventory inMemoryUserInventory = new InMemoryUserInventory();

    Library library = null;//new Library(inMemoryBookInventory, bookingInventory, inMemoryUserInventory, bookRepository, bookingRepository);

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
       int userId=3;

        //when
        final Booking rent = library.rent(book.get().getId(),userId);

        //then
        Collection<Booking> bookings = library.getRentedBooksForUser(userId);
        assertEquals(1, bookings.size());
    }

    @Test
    public void shouldRentTwoBooks() {

        //given
        Optional<Book> book = inMemoryBookInventory.getById(8);
        Optional<Book> book2 = inMemoryBookInventory.getById(7);
        int userId=3;

        //when
        final Booking rent = library.rent(book2.get().getId(),userId);
        final Booking rent2 = library.rent(book.get().getId(),userId);

        //then
        //rent.getUser()==user
        //rent.getBook() ==book
       /* assertEquals(book, rent.getBook());
        assertEquals(user, rent.getUser());*/

        Collection<Booking> bookings = library.getRentedBooksForUser(userId);
        assertEquals(2, bookings.size());
    }

    @Test
    public void shouldReturnBook() {

        //given
        Optional<Book> book = inMemoryBookInventory.getById(3);
        Optional<Book> book2 = inMemoryBookInventory.getById(6);
        Optional<Book> book3 = inMemoryBookInventory.getById(5);
        int userId=7;
        int userId2=2;
        Booking booking1 = library.rent(book.get().getId(), userId);
        Booking booking2 = library.rent(book2.get().getId(), userId);
        Booking booking3 = library.rent(book3.get().getId(), userId2);

        //when
        library.returnBook(booking2);

        //then
        assertEquals(2, bookingInventory.getBookings().size());

        Collection<Booking> bookings = library.getRentedBooksForUser(userId);
        assertEquals(1, bookings.size());
    }

    @Test
    public void shouldCheckIfBookNumberReachedLimit() {
        //given
        int userId=7;

        Optional<Book> book = inMemoryBookInventory.getById(3);
        Optional<Book> book2 = inMemoryBookInventory.getById(6);
        library.rent(book.get().getId(), userId);
        library.rent(book2.get().getId(),userId);

        //when
        final boolean checkBookLimit = library.checkBookLimit(userId);
        //then
        assertEquals(false, checkBookLimit);

    }

    @Test
    public void shouldCheckIfBookNumberNotReachedLimit() {
        //given
        int userId=7;

        Optional<Book> book = inMemoryBookInventory.getById(3);
        Optional<Book> book2 = inMemoryBookInventory.getById(6);
        Optional<Book> book3 = inMemoryBookInventory.getById(1);
        library.rent(book.get().getId(), userId);
        library.rent(book2.get().getId(), userId);
        library.rent(book3.get().getId(), userId);

        //when
        final boolean checkBookLimit = library.checkBookLimit(userId);
        //then
        assertEquals(true, checkBookLimit);

    }

    @Test
    public void shouldCheckIfBookRented() {
        //given
        int userId=7;

        Optional<Book> book = inMemoryBookInventory.getById(3);
        library.rent(book.get().getId(), userId);

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
        int userId=7;


        //when
        library.rent(book.get().getId(), userId);
        List<SearchBookView> searchBookView = library.searchBookView(Filters.title("an"));

        //then
        assertEquals(3, searchBookView.size());
    }

    @Test
    public void shouldCalculateReturnDate() {

        //given
        Optional<Book> book = inMemoryBookInventory.getById(12);
        int userId=6;
        library.rent(book.get().getId(), userId);
        Optional<Booking> booking = bookingInventory.getById(0);

        //when
        LocalDate returnDate = bookingInventory.calculateReturnDate(booking.get().getDate());

        //then
        assertEquals(LocalDate.now().plusMonths(1), returnDate);
    }

    @Test
    public void shouldNotFindBook() {

        //given
        int userId=2;

        final int bookId = 23;

        try {
            //when
            Booking booking = library.rent(bookId, userId);
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




