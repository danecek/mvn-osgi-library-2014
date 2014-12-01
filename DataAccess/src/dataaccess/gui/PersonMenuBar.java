/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess.gui;

import javax.swing.JMenuBar;

/**
 *
 * @author danecek
 */
public class PersonMenuBar extends JMenuBar {

    public PersonMenuBar() {
        add(new PersonMenu());
    }
    
    
}
