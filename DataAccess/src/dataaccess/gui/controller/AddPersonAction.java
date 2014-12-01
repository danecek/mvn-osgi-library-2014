/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess.gui.controller;

import dataaccess.gui.AddPersonDialog;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 *
 * @author danecek
 */
public class AddPersonAction extends AbstractAction {
    
    public static AddPersonAction instance = new AddPersonAction();

    public AddPersonAction() {
        super("Add Person");
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        new AddPersonDialog();

    }

}
