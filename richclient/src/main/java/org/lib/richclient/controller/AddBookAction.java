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
import org.lib.richclient.AddBookDialog;
import org.lib.richclient.ApplicationState;
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
        new AddBookDialog().show();
    }

}
