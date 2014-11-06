/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.richclient;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.lib.richclient.controller.Validator;

/**
 *
 * @author danecek
 */
public abstract class AbstractLibDialog extends Stage implements Validator {

    Button cancelButton = new Button("Cancel");
    Button okButton = new Button("OK");
    Text errorMessage = new Text();

    public AbstractLibDialog(String title) {
//        errorMessage.setStyle("-fx-fill: red");
        setTitle(title);
        setAlwaysOnTop(true);
        setResizable(false);
        this.centerOnScreen();
        this.initOwner(MainWindow.getInstance());
        this.initModality(Modality.APPLICATION_MODAL);
        HBox butons = new HBox(okButton, cancelButton);
        butons.setAlignment(Pos.BASELINE_RIGHT);
        VBox vbox = new VBox(createInterior(), butons, errorMessage);
        Scene s = new Scene(vbox);
        setScene(s);
        show();
    }
    
    protected Button getOkButon() {
        return okButton;
    }


    protected abstract Node createInterior();


}
