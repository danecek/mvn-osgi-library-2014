/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.integeration;

import java.util.Collection;
import org.lib.model.Book;
import org.lib.model.BookId;

/**
 *
 * @author danecek
 */
public interface BookDAO {

    void create(BookId id, String title, String author);
    Collection<Book> getAll();

}
