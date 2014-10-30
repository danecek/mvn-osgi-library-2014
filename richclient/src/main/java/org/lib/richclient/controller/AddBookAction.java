/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.richclient.controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import org.lib.business.LibraryFacade;
import org.lib.utils.LibraryException;

/**
 *
 * @author danecek
 */
public class AddBookAction extends LibraryAction {

    public static final AddBookAction INSTANCE = new AddBookAction();

    public AddBookAction() {
        super("Add Book"); // todo
    }

    @Override
    public void handle(ActionEvent t) {
        try {
            LibraryFacade.getInstance().createBook("foo", "foo");
            System.out.println(LibraryFacade.getInstance().getAllBooks());
        } catch (LibraryException ex) {
            Logger.getLogger(AddBookAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
