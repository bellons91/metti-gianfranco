package com.GroupTest.GroupTest.book;

import java.util.ArrayList;

public class Book {
    private final int id;
    private final String title;
    private final ArrayList<Author> authors;

    public Book(int id, String title, ArrayList<Author> authors){
        this.id = id;
        this.title = title;
        this.authors = authors;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", authors=" + authors +
                '}';
    }
}
