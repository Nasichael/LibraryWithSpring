package org.halas.agnieszka.library.data;

import java.time.LocalDate;

public class SearchBookView {

    private LocalDate date;
    private String author;
    private short year;
    private CategoryBook categoryBook;
    private String title;
    private int id;
    private BookStatus bookStatus;

    public SearchBookView(int id, String author, CategoryBook categoryBook, String title, short year, BookStatus bookStatus, LocalDate date) {

        this.id = id;
        this.date = date;
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

    @Override
    public String toString() {
        return
                "[author='" + author + '\'' +
                        ", title='" + title + '\'' +
                        ", categoryBook=" + categoryBook +
                        ", year=" + year +
                        ", id=" + id +
                        ", Status=" + bookStatus +
                        ", Return Date=" + date + "]";

    }
}
