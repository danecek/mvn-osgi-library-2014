package org.lib.model;

public class Book {
    
    private final BookId id;

    private final String title;
    private final String author;

    public Book(BookId id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    /**
     * @return the id
     */
    public BookId getId() {
        return id;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }
    

}