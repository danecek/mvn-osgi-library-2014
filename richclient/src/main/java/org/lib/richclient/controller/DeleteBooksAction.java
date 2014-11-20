/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.richclient.controller;

import javafx.event.ActionEvent;
import org.lib.richclient.DeleteBooksDialog;

/**
 *
 * @author danecek
 */
public class DeleteBooksAction extends LibraryAction {

    public static final DeleteBooksAction INSTANCE = new DeleteBooksAction();

    public DeleteBooksAction() {
        super("Delete Books"); // todo
    }

    @Override
    public void handle(ActionEvent t) {
        new DeleteBooksDialog().show();
    }

}
