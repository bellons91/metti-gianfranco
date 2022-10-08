package com.GroupTest.GroupTest.book;

public class Author {
    private final String name;

    public Author(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return "Author{" +
                "name='" + name + '\'' +
                '}';
    }
}
