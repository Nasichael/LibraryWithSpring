package org.halas.agnieszka.library;

import org.halas.agnieszka.library.data.Book;
import org.halas.agnieszka.library.data.CategoryBook;
import org.halas.agnieszka.library.inventory.BookRepository;
import org.halas.agnieszka.library.inventory.BookingInventory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class LibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}

	@Bean
	ApplicationRunner run(BookRepository bookRepository, BookingInventory bookingInventory)
	{
		return new ApplicationRunner() {
			@Override
			public void run(ApplicationArguments args) throws Exception {
				bookingInventory.getBookings();
				testmethod(bookRepository);
			}
		};
	}

	private void testmethod(BookRepository bookRepository) {
		//save book
		final Book newBook = new Book("a", (short) 333, CategoryBook.SCIENCEFICTION, "aaa", 4);
		bookRepository.save(newBook);
		//bookRepository.

		// get all books
		final List<Book> all = bookRepository.findAll();
		System.out.println(all.size());
	}

	@Bean
	ApplicationRunner writeSomething( )
	{
		return args -> System.out.println("something");
	}
}
