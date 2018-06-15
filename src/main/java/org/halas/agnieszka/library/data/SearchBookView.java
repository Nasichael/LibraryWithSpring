package org.halas.agnieszka.library.data;

import java.time.LocalDate;

public class SearchBookView {

    private LocalDate returnDate;
    private String author;
    private short year;
    private CategoryBook categoryBook;
    private String title;
    private int id;
    private BookStatus bookStatus;

    public SearchBookView(int id, String author, CategoryBook categoryBook, String title, short year, BookStatus bookStatus, LocalDate returnDate) {

        this.id = id;
        this.returnDate = returnDate;
        this.author = author;
        this.year = year;
        this.categoryBook = categoryBook;
        this.title = title;
        this.bookStatus = bookStatus;
    }

    public SearchBookView(int id, String author, CategoryBook categoryBook, String title, short year, BookStatus bookStatus) {
        this.author = author;
        this.year = year;
        this.categoryBook = categoryBook;
        this.title = title;
        this.id = id;
        this.bookStatus = bookStatus;
    }

    public LocalDate getDate() {
        return returnDate;
    }

    public String getAuthor() {
        return author;
    }

    public short getYear() {
        return year;
    }

    public CategoryBook getCategoryBook() {
        return categoryBook;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public BookStatus getBookStatus() {
        return bookStatus;
    }

    @Override
    public String toString() {
        return
                "[author='" + author + '\'' +
                        ", title='" + title + '\'' +
                        ", categoryBook=" + categoryBook +
                        ", year=" + year +
                        ", id=" + id +
                        ", Status=" + bookStatus +
                        ", Return Date=" + returnDate + "]";

    }
}
