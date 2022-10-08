package com.GroupTest.GroupTest.book;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.net.ssl.SSLSession;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {

    private static HttpBook mockHttpBook;
    private static BookService b;

    @BeforeAll
    static void setup(){
        mockHttpBook = Mockito.mock(HttpBook.class);
        b = new BookService(mockHttpBook);
    }

    @Test
    void shouldReturnNullIfCityNameNull(){
        Mockito.when(mockHttpBook.getResult(null)).thenReturn(null );


        assertThrows(
                IllegalArgumentException.class,
                () -> b.getBooks(null),
                "Expected getBooks(\"null\") to throw, but it didn't"
        );
    }

    @Test
    void shouldReturnErrorIfCityNameDoesntGetResults(){
        Mockito.when(mockHttpBook.getResult("shgbvskvr")).thenReturn(new HttpResponse() {
            @Override
            public int statusCode() {
                return 200;
            }

            @Override
            public HttpRequest request() {
                return null;
            }

            @Override
            public Optional<HttpResponse> previousResponse() {
                return Optional.empty();
            }

            @Override
            public HttpHeaders headers() {
                return null;
            }

            @Override
            public Object body() {
                return "{\"count\":0,\"next\":null,\"previous\":null,\"results\":[]}";
            }

            @Override
            public Optional<SSLSession> sslSession() {
                return Optional.empty();
            }

            @Override
            public URI uri() {
                return null;
            }

            @Override
            public HttpClient.Version version() {
                return null;
            }
        });


        assertThrows(
                IllegalArgumentException.class,
                () -> b.getBooks("shgbvskvr"),
                "Expected getBooks(\"shgbvskvr\") to throw, but it didn't"
        );
    }

    @Test
    void souldReturnArrayListWithABook(){
        Author expectedAuthor = new Author("De Amicis, Edmondo");
        ArrayList<Author> authors = new ArrayList<>();
        authors.add(expectedAuthor);
        Book expectedBook = new Book(19883, "Speranze e glorie; Le tre capitali: Torino, Firenze, Roma", authors);
        ArrayList<Book> expectedRes = new ArrayList<>();
        expectedRes.add(expectedBook);
        Mockito.when(mockHttpBook.getResult("Torino")).thenReturn(new HttpResponse() {
            @Override
            public int statusCode() {
                return 200;
            }

            @Override
            public HttpRequest request() {
                return null;
            }

            @Override
            public Optional<HttpResponse> previousResponse() {
                return Optional.empty();
            }

            @Override
            public HttpHeaders headers() {
                return null;
            }

            @Override
            public Object body() {
                return "{\"count\":1,\"next\":null,\"previous\":null,\"results\":[{\"id\":19883,\"title\":\"Speranze e glorie; Le tre capitali: Torino, Firenze, Roma\",\"authors\":[{\"name\":\"De Amicis, Edmondo\",\"birth_year\":1846,\"death_year\":1908}],\"translators\":[],\"subjects\":[\"Cavallotti, Felice, 1842-1898\",\"Florence (Italy) -- Description and travel\",\"Garibaldi, Giuseppe, 1807-1882\",\"Italy -- History -- 19th century\",\"Rome (Italy) -- Description and travel\",\"Socialism -- Italy -- History -- 19th century\",\"Statesmen -- Italy -- Biography\",\"Turin (Italy) -- Description and travel\"],\"bookshelves\":[\"IT Scienze politiche\"],\"languages\":[\"it\"],\"copyright\":false,\"media_type\":\"Text\",\"formats\":{\"text/plain; charset=iso-8859-1\":\"https://www.gutenberg.org/files/19883/19883-8.txt\",\"image/jpeg\":\"https://www.gutenberg.org/cache/epub/19883/pg19883.cover.medium.jpg\",\"application/x-mobipocket-ebook\":\"https://www.gutenberg.org/ebooks/19883.kindle.images\",\"application/rdf+xml\":\"https://www.gutenberg.org/ebooks/19883.rdf\",\"text/html\":\"https://www.gutenberg.org/ebooks/19883.html.images\",\"application/epub+zip\":\"https://www.gutenberg.org/ebooks/19883.epub.images\",\"text/plain\":\"https://www.gutenberg.org/ebooks/19883.txt.utf-8\"},\"download_count\":13}]}";
            }

            @Override
            public Optional<SSLSession> sslSession() {
                return Optional.empty();
            }

            @Override
            public URI uri() {
                return null;
            }

            @Override
            public HttpClient.Version version() {
                return null;
            }
        });


        ArrayList<Book> res = b.getBooks("Torino");


        assertEquals(res.toString(), expectedRes.toString());
    }

    @Test
    void shouldReturnArrayListWithAllBooks(){
        Author expectedAuthor = new Author("De Amicis, Edmondo");
        ArrayList<Author> authors = new ArrayList<>();
        authors.add(expectedAuthor);
        Book expectedBook = new Book(19883, "Speranze e glorie; Le tre capitali: Torino, Firenze, Roma", authors);

        Author expectedAuthor2 = new Author("Carocci, Guido");
        ArrayList<Author> authors2 = new ArrayList<>();
        authors2.add(expectedAuthor2);
        Book expectedBook2 = new Book(41555, "Il Valdarno da Firenze al mare", authors2);

        ArrayList<Book> expectedRes = new ArrayList<>();
        expectedRes.add(expectedBook);
        expectedRes.add(expectedBook2);
        Mockito.when(mockHttpBook.getResult("Firenze")).thenReturn(new HttpResponse() {
            @Override
            public int statusCode() {
                return 200;
            }

            @Override
            public HttpRequest request() {
                return null;
            }

            @Override
            public Optional<HttpResponse> previousResponse() {
                return Optional.empty();
            }

            @Override
            public HttpHeaders headers() {
                return null;
            }

            @Override
            public Object body() {
                return "{\"count\":2,\"next\":null,\"previous\":null,\"results\":[{\"id\":19883,\"title\":\"Speranze e glorie; Le tre capitali: Torino, Firenze, Roma\",\"authors\":[{\"name\":\"De Amicis, Edmondo\",\"birth_year\":1846,\"death_year\":1908}],\"translators\":[],\"subjects\":[\"Cavallotti, Felice, 1842-1898\",\"Florence (Italy) -- Description and travel\",\"Garibaldi, Giuseppe, 1807-1882\",\"Italy -- History -- 19th century\",\"Rome (Italy) -- Description and travel\",\"Socialism -- Italy -- History -- 19th century\",\"Statesmen -- Italy -- Biography\",\"Turin (Italy) -- Description and travel\"],\"bookshelves\":[\"IT Scienze politiche\"],\"languages\":[\"it\"],\"copyright\":false,\"media_type\":\"Text\",\"formats\":{\"text/plain; charset=iso-8859-1\":\"https://www.gutenberg.org/files/19883/19883-8.txt\",\"image/jpeg\":\"https://www.gutenberg.org/cache/epub/19883/pg19883.cover.medium.jpg\",\"application/x-mobipocket-ebook\":\"https://www.gutenberg.org/ebooks/19883.kindle.images\",\"application/rdf+xml\":\"https://www.gutenberg.org/ebooks/19883.rdf\",\"text/html\":\"https://www.gutenberg.org/ebooks/19883.html.images\",\"application/epub+zip\":\"https://www.gutenberg.org/ebooks/19883.epub.images\",\"text/plain\":\"https://www.gutenberg.org/ebooks/19883.txt.utf-8\"},\"download_count\":13},{\"id\":41555,\"title\":\"Il Valdarno da Firenze al mare\",\"authors\":[{\"name\":\"Carocci, Guido\",\"birth_year\":1851,\"death_year\":1916}],\"translators\":[],\"subjects\":[\"Arno River Valley (Italy) -- Description and travel\",\"Art -- Italy\"],\"bookshelves\":[\"IT Geografia\"],\"languages\":[\"it\"],\"copyright\":false,\"media_type\":\"Text\",\"formats\":{\"text/html\":\"https://www.gutenberg.org/files/41555/41555-h/41555-h.htm\",\"text/plain; charset=iso-8859-1\":\"https://www.gutenberg.org/files/41555/41555-8.txt\",\"application/epub+zip\":\"https://www.gutenberg.org/ebooks/41555.epub.images\",\"application/rdf+xml\":\"https://www.gutenberg.org/ebooks/41555.rdf\",\"text/plain\":\"https://www.gutenberg.org/ebooks/41555.txt.utf-8\",\"application/x-mobipocket-ebook\":\"https://www.gutenberg.org/ebooks/41555.kindle.images\",\"image/jpeg\":\"https://www.gutenberg.org/cache/epub/41555/pg41555.cover.medium.jpg\"},\"download_count\":8}]}";}

            @Override
            public Optional<SSLSession> sslSession() {
                return Optional.empty();
            }

            @Override
            public URI uri() {
                return null;
            }

            @Override
            public HttpClient.Version version() {
                return null;
            }
        });


        ArrayList<Book> res = b.getBooks("Firenze");


        assertEquals(res.toString(), expectedRes.toString());
    }

}