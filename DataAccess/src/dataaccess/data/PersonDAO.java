/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess.data;

import java.util.Collection;

/**
 *
 * @author danecek
 */
public interface PersonDAO {

    void create(String name);

    Collection<Person> getAll();
    
}
