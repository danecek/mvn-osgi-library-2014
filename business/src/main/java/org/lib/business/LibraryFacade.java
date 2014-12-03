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
import org.osgi.util.tracker.ServiceTracker;

/**
 *
 * @author danecek
 */
public abstract class LibraryFacade {

    private static LibraryFacade instance;
    private static ServiceTracker<LibraryFacade, LibraryFacade> st;

    /**
     * @return the instance
     */
    public static LibraryFacade getInstance() {
        if (instance == null) {
            instance = st.getService();
            if (instance == null) {
                instance = new DefaultLibraryFacade();
            }
        }
        return instance;
    }

    /**
     * @param aSt the st to set
     */
    public static void setSt(ServiceTracker<LibraryFacade, LibraryFacade> aSt) {
        st = aSt;
    }

    public abstract void createBook(String title, String author) throws LibraryException;

    public abstract void borrowBooks(LibReaderId readerId, Collection<BookId> books) throws LibraryException;

    public abstract Collection<Book> getAllBooks() throws LibraryException;

    public abstract void deleteBook(BookId id) throws LibraryException;

}
