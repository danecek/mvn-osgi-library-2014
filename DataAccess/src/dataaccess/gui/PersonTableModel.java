/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess.gui;

import dataaccess.data.Person;
import dataaccess.data.PersonDAOFactory;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author danecek
 */
public final class PersonTableModel extends AbstractTableModel {

    List<Person> personList;

    public PersonTableModel() {
        refresh();       
    }

    @Override
    public String getColumnName(int col) {
        switch (col) {
            case 0:
                return "Id";
            case 1:
                return "Name";
        }
        return "";
    }

    @Override
    public int getRowCount() {
        return personList.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Person p = personList.get(row);
        switch (col) {
            case 0:
                return p.getId();
            case 1:
                return p.getName();
        }
        return null;
    }

    public void refresh() {
        personList = new ArrayList<>(PersonDAOFactory.instance.getPersonDAO().getAll());
        System.out.println(personList);
        this.fireTableDataChanged();
    }

}
