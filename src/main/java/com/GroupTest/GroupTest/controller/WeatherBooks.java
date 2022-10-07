package com.GroupTest.GroupTest.controller;

import com.GroupTest.GroupTest.book.Book;

import java.util.ArrayList;

public class WeatherBooks {

    private Weather weather;
    private ArrayList<Book> books;

    public WeatherBooks(Weather weather, ArrayList<Book> books) {
        this.weather = weather;
        this.books = books;
    }
}
