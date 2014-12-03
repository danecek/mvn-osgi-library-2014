/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.connection;

import javafx.event.ActionEvent;
import org.lib.richclient.controller.LibraryAction;

public class ConnectAction extends LibraryAction {

    public static final ConnectAction instance = new ConnectAction();

    public ConnectAction() {
        super("Connect");
    }

    @Override
    public void handle(ActionEvent t) {
        new ConnectDialog().show();
    }

    @Override
    protected boolean feasible() {
        return !Connection.instance.isConnected();
    }

}
