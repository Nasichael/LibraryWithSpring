package org.halas.agnieszka.library.data;

public class CD {

    private String author;
    private short year;
    private CategoryCD categoryCD;
    private String name;
    private int id;

    public CD(String author, short year, CategoryCD categoryCD, String name, int id) {
        this.author = author;
        this.year = year;
        this.categoryCD = categoryCD;
        this.name = name;
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    public CategoryCD getCategoryCD() {
        return categoryCD;
    }

    public String getName() {
        return name;
    }
}
