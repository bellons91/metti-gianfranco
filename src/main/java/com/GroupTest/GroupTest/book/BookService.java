package com.GroupTest.GroupTest.book;

import com.google.gson.*;
import java.util.*;

public class BookService implements IBookService{
    private final HttpBook httpBook;

    public BookService(HttpBook httpBook) {
        this.httpBook = httpBook;
    }

    @Override
    public ArrayList<Book> getBooks(String cityName) {

        if (cityName == null || cityName.isEmpty() || cityName.isBlank()) throw new IllegalArgumentException("Parametro invalido");

        String json = (String) httpBook.getResult(cityName).body();

        ArrayList<Book> res = parseResult(json);

        if (res.isEmpty()){
            throw new IllegalArgumentException("Nessun libro con la città specificata: " + cityName);
        }

        return res;
    }

    private ArrayList<Book> parseResult(String json) {
        ArrayList<Book> result = new ArrayList<>();

        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();

        jsonObject.remove("count");
        jsonObject.remove("next");
        jsonObject.remove("previous");

        JsonArray allBooks = jsonObject.getAsJsonArray("results");
        for (JsonElement elem : allBooks){
            elem.getAsJsonObject().remove("translators");
            elem.getAsJsonObject().remove("subjects");
            elem.getAsJsonObject().remove("bookshelves");
            elem.getAsJsonObject().remove("languages");
            elem.getAsJsonObject().remove("copyright");
            elem.getAsJsonObject().remove("media_type");
            elem.getAsJsonObject().remove("formats");
            elem.getAsJsonObject().remove("download_count");

            JsonArray authors = elem.getAsJsonObject().getAsJsonArray("authors");
            for (JsonElement auth : authors){
                auth.getAsJsonObject().remove("birth_year");
                auth.getAsJsonObject().remove("death_year");
            }
        }


        for (int i=0;i<allBooks.size();i++)
            result.add(new Gson().fromJson(allBooks.get(i), Book.class));

        return result;
    }
}
