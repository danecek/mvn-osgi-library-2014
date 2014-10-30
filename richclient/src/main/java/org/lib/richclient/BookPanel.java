/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.richclient;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import org.lib.business.LibraryFacade;
import org.lib.model.Book;
import org.lib.model.BookId;
import org.lib.utils.LibraryException;

/**
 *
 * @author danecek
 */
public class BookPanel extends VBox {

    private Label createTitle() {
        Label title = new Label("Books");
        title.setStyle("-fx-font: 16px \"Serif\";\n" +
"    -fx-padding: 10;\n" +
"    -fx-background-color: #CCFF99;");
        return title;
    }

    ObservableList<Book> data = FXCollections.observableArrayList();

    private ScrollPane createTable() {
        TableView<Book> tab = new TableView<Book>();
        TableColumn<Book, BookId> idCol = new TableColumn<>("Id");  // todo lok
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        tab.getColumns().addAll(idCol);
        tab.setItems(data);
        return new ScrollPane(tab);

    }

    public BookPanel() {
        getChildren().addAll(createTitle(), createTable());
        try {
            data.addAll(LibraryFacade.getInstance().getAllBooks());
        } catch (LibraryException ex) {
            Logger.getLogger(BookPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
