/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.protocol;

import org.lib.business.LibraryFacade;
import org.lib.utils.LibraryException;

/**
 *
 * @author danecek
 */
public class CreateBook extends AbstractCommand<String> {

    private String title;
    private String author;

    public CreateBook(String title, String author) {
        this.title = title;
        this.author = author;
    }

    @Override
    public String execute(LibraryFacade f) throws LibraryException {
        f.createBook(title, author);
        return OK;
    }
}
