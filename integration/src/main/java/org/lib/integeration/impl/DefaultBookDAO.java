/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.integeration.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.lib.integeration.BookDAO;
import org.lib.model.Book;
import org.lib.model.BookId;

/**
 *
 * @author danecek
 */
public class DefaultBookDAO implements BookDAO {

    Map<BookId, Book> booksMap = new HashMap<>();

    @Override
    public void create(BookId id, String title, String author) {
        booksMap.put(id, new Book(id, title, author));
    }

    @Override
    public Collection<Book> getAll() {
      return new ArrayList<>(booksMap.values());
    }

}
