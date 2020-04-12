package com.willgoodman.bookstore;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Arrays;
import java.util.TreeSet;
import java.util.Comparator;

/**
 * Represents a book store which can store up 100000 books
 *
 * @author Will Goodman
 */
public class Bookstore {

    private static final HashSet GENRE_CODES = new HashSet(Arrays.asList("HRR", "SCF", "CRI", "NFI", "ART"));
    private static final String VALID_BOOK_ID_FORMAT = "^([a-zA-Z]{6})([a-zA-Z]{3})(\\d{5})$";
    private static final int AUTHOR_REGEX_GROUP = 1;
    private static final int GENRE_REGEX_GROUP = 2;
    private static final int UNIQUE_ID_REGEX_GROUP = 3;
    private static final String VALID_AUTHOR_NAME = "^\\w+ \\w+( \\w+)?$";
    private static final String INVALID_AUTHOR_ID_CHARACTERS = "[ AEIOU]";
    private static final int AUTHOR_BOOK_ID_LENGTH = 6;
    private static final int MIN_UNIQUE_ID = 1;
    private static final int MAX_UNIQUE_ID = 99999;

    private HashMap<String, Integer> books;
    private TreeSet<Integer> availableUniqueIDs;


    /**
     * Constructs a Bookstore object
     */
    public Bookstore() {
        availableUniqueIDs = new TreeSet<>();
        for (int number = MIN_UNIQUE_ID; number <= MAX_UNIQUE_ID; number++) {
            availableUniqueIDs.add(number);
        }

        books = new HashMap<>();
    }


    /**
     * Returns the total number of books in the store
     *
     * @return The total number of books in the store
     */
    public int getNumberOfBooks() {
        int totalNumberOfBooks = 0;

        for (int stockOfBook : books.values()) {
            totalNumberOfBooks += stockOfBook;
        }

        return totalNumberOfBooks;
    }


    /**
     * Gets the Book ID's of all books in a given genre
     *
     * @param genre The genre of the books to retrieve. Must be one of the valid genre codes
     * @return The list of books in the given genre
     */
    public ArrayList<String> getGenreBooks(String genre) {
        ArrayList<String> genreBooks = new ArrayList<>();

        if (GENRE_CODES.contains(genre)) {
            for (String bookID : books.keySet()) {
                if (Pattern.matches(String.format("^[a-zA-Z]{6}%s\\d{5}$", genre), bookID)) {
                    genreBooks.add(bookID);
                }
            }
        }

        return genreBooks;
    }


    /**
     * Adds a new book to the store
     *
     * @param author The name of the book's author. Must be either a two-name or three-name format
     * @param genre  The books genre. Must be one of the valid genre codes
     */
    public void addBook(String author, String genre) {
        if (Pattern.matches(VALID_AUTHOR_NAME, author) && GENRE_CODES.contains(genre) && !availableUniqueIDs.isEmpty()) {
            String bookID = generateBookID(author, genre);
            if (!books.containsKey(bookID)) {
                books.put(bookID, 1);
            }
        }
    }


    /**
     * Adds another copy of a book which already exists in the store
     *
     * @param bookID The ID number of the book to add
     */
    public void addBook(String bookID) {
        if (books.containsKey(bookID) && isValidID(bookID)) {
            books.put(bookID, books.get(bookID) + 1);
        }
    }


    /**
     * Removes a copy of a book from the store
     *
     * @param bookID The ID number of the book to remove
     */
    public void removeBook(String bookID) {
        Matcher matcher = Pattern.compile(VALID_BOOK_ID_FORMAT).matcher(bookID);

        if (books.containsKey(bookID) && matcher.find()) {
            int copiesOfBook = books.get(bookID);
            if (copiesOfBook == 1) {
                try {
                    // recycle the unique ID
                    availableUniqueIDs.add(Integer.parseInt(matcher.group(UNIQUE_ID_REGEX_GROUP)));
                    books.remove(bookID);
                } catch (NumberFormatException ex) {
                    // this should never be thrown as the Book ID is checked by the regex
                    System.out.println("BookID format incorrect. Book not removed.");
                }
            } else {
                books.put(bookID, copiesOfBook - 1);
            }
        }
    }


    /**
     * Checks if a given Book ID matches the required ID format
     *
     * @param bookID The ID to check
     * @return Whether or not the given ID matches the valid format
     */
    public boolean isValidID(String bookID) {
        Matcher matcher = Pattern.compile(VALID_BOOK_ID_FORMAT).matcher(bookID);

        return matcher.find() && GENRE_CODES.contains(matcher.group(GENRE_REGEX_GROUP));
    }


    /**
     * Checks if a given Book ID is in stock in the store
     *
     * @param bookID The ID to check
     * @return Whether or not the book is in stock
     */
    public boolean isInStock(String bookID) {
        return books.containsKey(bookID);
    }


    /**
     * Returns all the books in the store as a single string, in alphabetical order of the author name and on separate lines
     *
     * @return The string containing all the bookIDs in the store
     */
    public String booksToString() {
        ArrayList<String> bookIDs = new ArrayList(books.keySet());

        bookIDs.sort(new Comparator<String>() {
            @Override
            public int compare(String firstID, String secondID) {
                Matcher firstMatcher = Pattern.compile(VALID_BOOK_ID_FORMAT).matcher(firstID);
                Matcher secondMatcher = Pattern.compile(VALID_BOOK_ID_FORMAT).matcher(secondID);

                if (firstMatcher.find() && secondMatcher.find()) {
                    return firstMatcher.group(AUTHOR_REGEX_GROUP).compareTo(secondMatcher.group(AUTHOR_REGEX_GROUP));
                } else if (firstMatcher.find()) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });

        StringBuilder stringBuilder = new StringBuilder();
        for (String bookID : bookIDs) {
            stringBuilder.append(bookID);
            stringBuilder.append('\n');
        }

        return stringBuilder.toString();
    }


    /**
     * Generates a Book ID in the following format:
     * - The first six consonants from the author's name in upper case.
     * If there are less than six consonants, the character 'X' is used to pad the name out e.g. WLLGXX
     * - The genre code e.g. ART
     * - A unique, 5 digit integer in the range 1 to 99999. This increments as the ID's are used up.
     *
     * @param author The name of the Book's author
     * @param genre  The genre of the Book
     * @return The generated ID for the book
     */
    private String generateBookID(String author, String genre) {
        if (Pattern.matches(VALID_AUTHOR_NAME, author) && GENRE_CODES.contains(genre)) {
            StringBuilder stringBuilder = new StringBuilder();

            // format the author's name
            stringBuilder.append(author.toUpperCase().replaceAll(INVALID_AUTHOR_ID_CHARACTERS, ""));
            stringBuilder.append("XXXXXX");
            stringBuilder.setLength(AUTHOR_BOOK_ID_LENGTH);

            stringBuilder.append(genre);

            // pad out the unique ID to be five digits e.g. 1 becomes 00001
            stringBuilder.append(String.format("%05d", availableUniqueIDs.pollFirst()));

            return stringBuilder.toString();
        } else {
            return "Invalid author name or genre code.";
        }
    }

}