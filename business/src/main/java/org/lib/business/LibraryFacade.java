/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.business;

import java.util.Collection;
import org.lib.business.impl.DefaultLibraryFacade;
import org.lib.model.Book;
import org.lib.model.BookId;
import org.lib.model.LibReaderId;
import org.lib.utils.LibraryException;

/**
 *
 * @author danecek
 */
public abstract class LibraryFacade {

    private static LibraryFacade instance;

    /**
     * @return the instance
     */
    public static LibraryFacade getInstance() {
        if (instance == null) {
            // TODO
            instance = new DefaultLibraryFacade();
        }
        return instance;
    }

    public abstract void createBook(String title) throws LibraryException;

    public abstract void borrowBooks(LibReaderId readerId, Collection<BookId> books) throws LibraryException;

    public abstract Collection<Book> getAllBooks() throws LibraryException;

    public abstract void deleteBook(BookId id) throws LibraryException;
    

}
