package com.GroupTest.GroupTest.book;

import org.junit.jupiter.api.Test;


import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.*;

class HttpBookTest {
    @Test
    void shouldObtainStatus200(){
        HttpBook test = new HttpBook();

        HttpResponse res = test.getResult("Torino");

        assertEquals(200, res.statusCode());
    }

}