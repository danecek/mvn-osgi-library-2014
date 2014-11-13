/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.richclient;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import org.lib.business.LibraryFacade;
import org.lib.model.Book;
import org.lib.model.BookId;
import org.lib.richclient.controller.Validator;
import org.lib.utils.LibraryException;

/**
 *
 * @author danecek
 */
public final class DeleteBooksDialog extends AbstractLibDialog implements Validator {

    @Override
    public boolean validate() {
        return true;
    }

    @Override
    protected Node createInterior() {
        return new ListView<>(BookPanel.instance.getSelected());
    }

    public DeleteBooksDialog() {
        super("Add Book"); // todo

        getOkButon().setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                try {
                    for (Book b : BookPanel.instance.getSelected()) {
                        LibraryFacade.getInstance().deleteBook(b.getId());
                    }
                    DataState.INSTANCE.invalidate();
                    hide();
                } catch (LibraryException ex) {
                    Logger.getLogger(DeleteBooksDialog.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

}
