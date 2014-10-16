/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.business.impl;

import java.util.Collection;
import org.lib.business.LibraryFacade;
import org.lib.integration.AbstractDAOFactory;
import org.lib.integration.BookDAO;
import org.lib.integration.BorrowDAO;
import org.lib.model.BookId;
import org.lib.model.LibReaderId;
import org.lib.utils.LibraryException;

/**
 *
 * @author danecek
 */
public class DefaultLibraryFacade extends LibraryFacade {
    
    private final BookDAO booksDAO;
    private final BorrowDAO borrowsDAO;
    
    public DefaultLibraryFacade() {
        booksDAO = AbstractDAOFactory.getInstance().getBooksDAO();
        borrowsDAO = AbstractDAOFactory.getInstance().getBorrowDAO();
    }
    
    @Override
    public void createBook(String title, String author) throws LibraryException {
        booksDAO.create(title, author);
    }
    
    @Override
    public void borrowBooks(LibReaderId readerId, Collection<BookId> books) throws LibraryException {
        for (BookId id : books) {
            borrowsDAO.createBorrow(readerId, id);
        }
        
    }
    
}
