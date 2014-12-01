/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author danecek
 */
public class PersonDAOImpl implements PersonDAO {

    Map<Integer, Person> persons = new HashMap<>();
    static int idCounter;

    public PersonDAOImpl() {
        create("Novak");
        create("Svoboda");
    }

    @Override
    public void create(String name) {
        Person p = new Person(idCounter++, name);
        persons.put(p.getId(), p);
    }

    @Override
    public Collection<Person> getAll() {
        return new ArrayList<>(persons.values());
    }

}
