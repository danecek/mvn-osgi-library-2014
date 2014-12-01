/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 *
 * @author danecek
 */
public class AddPersonDialog extends JDialog {

    JButton ok = new JButton("OK");
    JButton cancel = new JButton("Cancel");

    public AddPersonDialog() {
        super((JFrame) null, "Add Person Dialog", true);
        
        cancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                AddPersonDialog.this.dispose();
            }
        });

        Box btns = new Box(BoxLayout.X_AXIS);
        btns.add(ok);
        btns.add(cancel);
        add(btns, BorderLayout.SOUTH);

        setBounds(100, 100, 100, 100);
        setVisible(true);
    }

}
