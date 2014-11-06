/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.richclient;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.swing.BorderFactory;
import org.lib.richclient.controller.AddBookAction;
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

    private HBox createToolbar() {
        HBox hbox = new HBox(AddBookAction.INSTANCE.createButton());
        hbox.setPadding(new Insets(2));//;Style("-fx-border-color: red;");
        //Border b = new Border(new BorderStroke(null, BorderStrokeStyle.NONE, CornerRadii.EMPTY, BorderWidths.EMPTY));
        //   hbox.setBorder();
        return hbox;
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
        this.setTitle(Library.createMessage());
        SplitPane splitPane = new SplitPane();
        splitPane.getItems().addAll(new BookPanel(), new BookPanel());
        VBox vb = new VBox(createMenubar(), createToolbar(), splitPane);
        Scene s = new Scene(vb, 800, 600);
        this.setScene(s);
        this.centerOnScreen();
        this.show();
    }

}
