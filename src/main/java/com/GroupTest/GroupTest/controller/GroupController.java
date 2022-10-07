package com.GroupTest.GroupTest.controller;

import com.GroupTest.GroupTest.book.IBookService;
import com.GroupTest.GroupTest.weather.IWeatherService;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class GroupController {

    private IWeatherService weatherService;
    private IBookService bookService;
    public GroupController(IWeatherService weatherService, IBookService bookService) {
        this.weatherService = weatherService;
        this.bookService = bookService;
    }

}
