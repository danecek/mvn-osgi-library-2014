/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess.data;

/**
 *
 * @author danecek
 */
public class MemoryPersonDAOFactory extends PersonDAOFactory {


    @Override
    public PersonDAO getPersonDAO() {
        return new PersonDAOImpl();

    }

}
