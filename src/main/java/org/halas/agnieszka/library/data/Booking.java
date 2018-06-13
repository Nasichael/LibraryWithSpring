package org.halas.agnieszka.library.data;

import java.time.LocalDate;

public class Booking {

    private static int counter = 0;
    private int id;
    private User user;
    private Book book;
    private LocalDate date;


    public Booking(int id, User user, Book book, LocalDate date) {
        this.id = id;
        this.user = user;
        this.book = book;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Book getBook() {
        return book;
    }

    public LocalDate getDate() {
        return date;
    }

    public static int getNextId()
    {
        return counter++;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", user=" + user +
                ", book=" + book +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Booking booking = (Booking) o;

        if (getId() != booking.getId()) return false;
        if (!getUser().equals(booking.getUser())) return false;
        if (!getBook().equals(booking.getBook())) return false;
        return getDate().equals(booking.getDate());
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getUser().hashCode();
        result = 31 * result + getBook().hashCode();
        result = 31 * result + getDate().hashCode();
        return result;
    }
}

