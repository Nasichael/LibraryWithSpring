package org.halas.agnieszka.library.data;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

//@Table(name = "shop_book")

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = SEQUENCE)
    //@Column(name="BOOK_ID")
    private int id;
    private String author;
    private short year;
    private CategoryBook categoryBook;
    private String title;


    public Book() {
    }

    public Book(String author, short year, CategoryBook categoryBook, String title, int id) {
        this.author = author;
        this.year = year;
        this.categoryBook = categoryBook;
        this.title = title;
        this.id = id;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setYear(short year) {
        this.year = year;
    }

    public void setCategoryBook(CategoryBook categoryBook) {
        this.categoryBook = categoryBook;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(int id) {
        this.id = id;
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


    @Override
    public String toString() {
        return "author: " + author +
                ", year: " + year +
                ", categoryBook: " + categoryBook +
                ", title: \"" + title + "\"" +
                ", id: " + id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (getYear() != book.getYear()) return false;
        if (getId() != book.getId()) return false;
        if (!getAuthor().equals(book.getAuthor())) return false;
        if (getCategoryBook() != book.getCategoryBook()) return false;
        return getTitle().equals(book.getTitle());
    }

    @Override
    public int hashCode() {
        int result = getAuthor().hashCode();
        result = 31 * result + (int) getYear();
        result = 31 * result + getCategoryBook().hashCode();
        result = 31 * result + getTitle().hashCode();
        result = 31 * result + getId();
        return result;
    }
}
