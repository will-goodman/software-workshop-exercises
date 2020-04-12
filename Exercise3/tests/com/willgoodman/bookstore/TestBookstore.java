package com.willgoodman.bookstore;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;

class TestBookstore {

    @Test
    void getNumberOfBooks() {
        Bookstore bookstore = new Bookstore();

        assert bookstore.getNumberOfBooks() == 0;

        bookstore.addBook("Robert Ludlum", "CRI");

        assert bookstore.getNumberOfBooks() == 1;
    }

    @Test
    void addBook() {
        Bookstore bookstore = new Bookstore();
        bookstore.addBook("Robert Ludlum", "CRI");

        assert bookstore.getNumberOfBooks() == 1;
        assert bookstore.isInStock("RBRTLDCRI00001");
    }

    @Test
    void addBookShortAuthorName() {
        Bookstore bookstore = new Bookstore();
        bookstore.addBook("C S Lewis", "SCF");

        assert bookstore.getNumberOfBooks() == 1;
        assert bookstore.isInStock("CSLWSXSCF00001");
    }

    @Test
    void addBookSameAuthorGenre() {
        Bookstore bookstore = new Bookstore();
        bookstore.addBook("Robert Ludlum", "CRI");
        bookstore.addBook("Robert Ludlum", "CRI");

        assert bookstore.getNumberOfBooks() == 2;
        assert bookstore.isInStock("RBRTLDCRI00001");
    }

    @Test
    void addBookInvalidGenre() {
        Bookstore bookstore = new Bookstore();
        bookstore.addBook("Bernard Cornwell", "INVALID_GENRE");

        assert bookstore.getNumberOfBooks() == 0;
    }

    @Test
    void addBookEmptyGenre() {
        Bookstore bookstore = new Bookstore();
        bookstore.addBook("Bernard Cornwell", "");
        assert bookstore.getNumberOfBooks() == 0;
    }

    @Test
    void addBookInvalidName() {
        Bookstore bookstore = new Bookstore();
        bookstore.addBook("James", "ART");
        assert bookstore.getNumberOfBooks() == 0;
    }

    @Test
    void addBookEmptyName() {
        Bookstore bookstore = new Bookstore();
        bookstore.addBook("", "ART");
        assert bookstore.getNumberOfBooks() == 0;
    }

    @Test
    void addBookThreeWordName() {
        Bookstore bookstore = new Bookstore();
        bookstore.addBook("Robert Louis Stevenson", "CRI");
        assert bookstore.getNumberOfBooks() == 1;
        assert bookstore.isInStock("RBRTLSCRI00001");
    }

    @Test
    void addBookNewCopy() {
        Bookstore bookstore = new Bookstore();
        bookstore.addBook("Robert Ludlum", "CRI");
        bookstore.addBook("RBRTLDCRI00001");

        assert bookstore.getNumberOfBooks() == 2;
        assert bookstore.isInStock("RBRTLDCRI00001");
    }

    @Test
    void addBookInvalidBookID() {
        Bookstore bookstore = new Bookstore();
        bookstore.addBook("Robert Ludlum", "CRI");
        bookstore.addBook("INVALID_BOOK_ID");

        assert bookstore.getNumberOfBooks() == 1;
        assert !bookstore.isInStock("INVALID_BOOK_ID");
    }

    @Test
    void removeBook() {
        Bookstore bookstore = new Bookstore();
        bookstore.addBook("Robert Ludlum", "CRI");
        bookstore.addBook("Robert Louis Stevenson", "CRI");

        bookstore.removeBook("RBRTLDCRI00001");

        assert bookstore.getNumberOfBooks() == 1;
        assert !bookstore.isInStock("RBRTLDCRI00001");
    }

    @Test
    void removeBookStillInStock() {
        Bookstore bookstore = new Bookstore();
        bookstore.addBook("Robert Ludlum", "CRI");
        bookstore.addBook("RBRTLDCRI00001");

        bookstore.removeBook("RBRTLDCRI00001");

        assert bookstore.getNumberOfBooks() == 1;
        assert bookstore.isInStock("RBRTLDCRI00001");
    }

    @Test
    void removeBookInvalidBookID() {
        Bookstore bookstore = new Bookstore();
        bookstore.addBook("Robert Ludlum", "CRI");

        bookstore.removeBook("INVALID_BOOK_ID");

        assert bookstore.getNumberOfBooks() == 1;
    }

    @Test
    void getGenreBooks() {
        Bookstore bookstore = new Bookstore();
        bookstore.addBook("Robert Ludlum", "CRI");
        bookstore.addBook("Robert Louis Stevenson", "CRI");
        bookstore.addBook("Joanne Kathleen Rowling", "SCF");

        ArrayList<String> expectedBooks = new ArrayList<>();
        expectedBooks.add("RBRTLDCRI00001");
        expectedBooks.add("RBRTLSCRI00002");

        assert bookstore.getGenreBooks("CRI").equals(expectedBooks);
    }

    @Test
    void getGenreBooksNoneExist() {
        Bookstore bookstore = new Bookstore();
        bookstore.addBook("Robert Ludlum", "CRI");
        bookstore.addBook("Robert Louis Stevenson", "CRI");
        bookstore.addBook("Joanne Kathleen Rowling", "SCF");

        assert bookstore.getGenreBooks("ART").equals(new ArrayList<>());
    }

    @Test
    void getGenreBooksInvalidGenre() {
        Bookstore bookstore = new Bookstore();
        bookstore.addBook("Robert Ludlum", "CRI");
        bookstore.addBook("Robert Louis Stevenson", "CRI");
        bookstore.addBook("Joanne Kathleen Rowling", "SCF");

        assert bookstore.getGenreBooks("INVALID_GENRE").equals(new ArrayList<>());
    }

    @Test
    void isValidID() {
        Bookstore bookstore = new Bookstore();
        assert bookstore.isValidID("RBRTLDCRI00001");
    }

    @Test
    void isValidIDInvalid() {
        Bookstore bookstore = new Bookstore();
        assert !bookstore.isValidID("INVALID_BOOK_ID");
    }

    @Test
    void isValidIDEmpty() {
        Bookstore bookstore = new Bookstore();
        assert !bookstore.isValidID("");
    }

    @Test
    void isInStock() {
        Bookstore bookstore = new Bookstore();
        bookstore.addBook("Robert Ludlum", "CRI");

        assert bookstore.isInStock("RBRTLDCRI00001");
    }

    @Test
    void isInStockSoldOut() {
        Bookstore bookstore = new Bookstore();
        bookstore.addBook("Robert Ludlum", "CRI");

        assert !bookstore.isInStock("RBRTLSCRI00002");
    }

    @Test
    void booksToString() {
        Bookstore bookstore = new Bookstore();
        bookstore.addBook("Robert Ludlum", "CRI");
        bookstore.addBook("Robert Louis Stevenson", "CRI");
        bookstore.addBook("Joanne Kathleen Rowling", "SCF");

        String expectedBookIDs = "JNNKTHSCF00003\nRBRTLDCRI00001\nRBRTLSCRI00002\n";

        assert bookstore.booksToString().equals(expectedBookIDs);
    }
}