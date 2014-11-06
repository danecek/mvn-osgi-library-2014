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
import javafx.scene.control.MenuBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import static org.lib.utils.Messages.Library;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.launch.Framework;

/**
 *
 * @author danecek
 */
public class MainWindow extends Stage {
    
    private static MainWindow instance;

    /**
     * @return the instance
     */
    public static MainWindow getInstance() {
        return instance;
    }

    private MenuBar createMenubar() {
        MenuBar mb = new MenuBar();
        mb.getMenus().addAll(new BookMenu());
        return mb;

    }

    public MainWindow(BundleContext context) {
        instance = this;
        setOnCloseRequest(new EventHandler<WindowEvent>() {

            @Override
            public void handle(WindowEvent t) {
                Framework f = (Framework) context.getBundle(0);
                try {
                    f.stop();
                    f.waitForStop(3000);
                } catch (BundleException | InterruptedException ex) {
                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        this.setTitle(Library.createMessage("Moje"));
        VBox vb = new VBox(createMenubar(), new BookPanel());
        Scene s = new Scene(vb, 800, 600);
        this.setScene(s);
        this.centerOnScreen();
        this.show();
    }

}
