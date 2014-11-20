/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.proxy.impl;

import java.util.Collection;
import org.lib.business.LibraryFacade;
import org.lib.connection.Connection;
import org.lib.model.Book;
import org.lib.model.BookId;
import org.lib.model.LibReaderId;
import org.lib.protocol.CreateBook;
import org.lib.protocol.GetAllBooks;
import org.lib.utils.LibraryException;

/**
 *
 * @author danecek
 */
public class LibraryFacadeProxy extends LibraryFacade {

    @Override
    public void createBook(String title, String author) throws LibraryException {
        CreateBook cb = new CreateBook(title, author);
        Connection.instance.send(cb);
    }

    @Override
    public Collection<Book> getAllBooks() throws LibraryException {
        return Connection.instance.send(new GetAllBooks());
    }
    
    @Override
    public void borrowBooks(LibReaderId readerId, Collection<BookId> books) throws LibraryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public void deleteBook(BookId id) throws LibraryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
