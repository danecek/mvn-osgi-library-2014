/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.derbydb.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lib.integration.BookDAO;
import org.lib.model.Book;
import org.lib.model.BookId;
import org.lib.utils.LibraryException;

/**
 *
 * @author danecek
 */
public class DerbyBookDAO implements BookDAO {

    PreparedStatement getAllPs;
    PreparedStatement createPs;
    Connection con;

    public DerbyBookDAO(Connection connection) {
        this.con = con;
        try {
            createPs = connection.prepareStatement("INSERT INTO BOOKS VALUES(DEFAULT, ?)");
//            deletePS = connection.prepareStatement("DELETE FROM BOOKS WHERE ID = ?");
            getAllPs = connection.prepareStatement("SELECT * FROM BOOKS");
        } catch (SQLException ex) {
            Logger.getLogger(DerbyBookDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void create(String title) throws LibraryException {
        try {
            createPs.setString(1, title);
            int n = createPs.executeUpdate();
            if (n != 1) {
                throw new LibraryException("creation failed");
            }
        } catch (SQLException ex) {
            throw new LibraryException(ex);
        }
    }

    public Collection<Book> getAll() throws LibraryException {
        ResultSet rs;
        try {
            rs = getAllPs.executeQuery();
            ArrayList<Book> books = new ArrayList<Book>();
            while (rs.next()) {
                books.add(new Book(new BookId(rs.getInt(1)), rs.getString(2)));
            }
            return books;
        } catch (SQLException ex) {
            throw new LibraryException(ex);
        }

    }

    public void delete(BookId id) throws LibraryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void update(Book reader) throws LibraryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Book find(BookId id) throws LibraryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
