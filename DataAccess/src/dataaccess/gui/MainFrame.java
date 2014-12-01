/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess.gui;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import javax.swing.JFrame;

/**
 *
 * @author danecek
 */
public class MainFrame extends JFrame {

    public MainFrame() throws HeadlessException {
        super("Persons");
        setJMenuBar(new PersonMenuBar());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      //  setLocation(200, 200);
        setBounds(100, 100, 600, 400);
        add(new PersonPanel(), BorderLayout.CENTER);
//        pack();
        setVisible(true);
    }

}
