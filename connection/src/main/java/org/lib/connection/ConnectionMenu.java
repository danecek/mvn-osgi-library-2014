/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.connection;

import javafx.scene.control.Menu;

public class ConnectionMenu extends Menu {

    public ConnectionMenu() {
        super("Connection");
        getItems().addAll(ConnectAction.instance.createMenuItem(),
                DisconnectAction.instance.createMenuItem());
    }

}
