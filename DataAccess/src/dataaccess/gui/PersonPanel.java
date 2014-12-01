/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess.gui;

import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author danecek
 */
public class PersonPanel extends JScrollPane {

    JTable personTable;

    public PersonPanel() {
        super(new JTable(new PersonTableModel()));
  //      this.pu
//'/        add(personTable = new JTable(new PersonTableModel()));
    }

}
