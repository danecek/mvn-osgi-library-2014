/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import dataaccess.gui.MainFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author danecek
 */
public class DataAccess {

    public static void main(String[] args) {
//        new MainFrame();
        
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new MainFrame();
            }
        });
    }
    
}
