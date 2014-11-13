package org.lib.model;

public class Book {
    
    private final BookId id;
    private final String title;

    public Book(BookId id, String title) {
        this.id = id;
        this.title = title;
    }

    @Override
    public String toString() {
        return "Book{" + "id=" + id.getId() + ", title=" + title + "}";
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


}
