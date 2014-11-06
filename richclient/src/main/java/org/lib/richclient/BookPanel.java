/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.richclient;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import org.lib.business.LibraryFacade;
import org.lib.model.Book;
import org.lib.model.BookId;
import org.lib.utils.LibraryException;

/**
 *
 * @author danecek
 */
public final class BookPanel extends TitledPane implements InvalidationListener {

    private Label createTitle() {
        Label title = new Label("Books");
        title.setStyle("-fx-font: 16px \"Serif\";\n"
                + "    -fx-padding: 10;\n"
                + "    -fx-background-color: #CCFF99;");
        return title;
    }

    ObservableList<Book> data = FXCollections.observableArrayList();

    private TableView<Book> createTable() {
        TableView<Book> tab = new TableView<Book>();
        TableColumn<Book, BookId> idCol = new TableColumn<>("Id");  // todo lok
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Book, BookId> titleCol = new TableColumn<>("Title");  // todo lok
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        TableColumn<Book, BookId> authorCol = new TableColumn<>("Author");  // todo lok
        authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
        tab.getColumns().addAll(idCol, titleCol, authorCol);
        tab.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        tab.getSelectionModel().getSelectedItems().addListener(new InvalidationListener() {

            @Override
            public void invalidated(Observable observable) {
                System.out.println(observable);//     throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        tab.setItems(data);
        return tab;
    }

    public BookPanel() {
        setText("Books"); //todo
        setContent(createTable());
        DataState.INSTANCE.addListener(this);
        getChildren().addAll(createTitle(), createTable());
        invalidated(null);
    }

    Book getSelected() {
        TableView<Book> tab = null;
        return tab.getSelectionModel().getSelectedItem();
    }

    @Override
    public void invalidated(Observable o) {
        data.clear();
        try {
            data.addAll(LibraryFacade.getInstance().getAllBooks());
        } catch (LibraryException ex) {
            Logger.getLogger(BookPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
