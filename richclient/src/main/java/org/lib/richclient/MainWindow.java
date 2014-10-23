/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.richclient;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.launch.Framework;

/**
 *
 * @author danecek
 */
public class MainWindow extends Stage {

    public MainWindow(BundleContext context) {
        setOnCloseRequest(new EventHandler<WindowEvent>() {

            @Override
            public void handle(WindowEvent t) {
                Framework f = (Framework) context.getBundle(0);
                try {
                    f.stop();
                    f.waitForStop(3000);
                } catch (BundleException ex) {
                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        this.setTitle("Library");
        Scene s = new Scene(new Label("****** Scena *******"), 800, 600);
        this.setScene(s);
        this.centerOnScreen();
        this.show();
    }

}
