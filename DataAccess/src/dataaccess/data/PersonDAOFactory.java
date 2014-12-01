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
public abstract class PersonDAOFactory {

    public static MemoryPersonDAOFactory instance = new MemoryPersonDAOFactory();

    public abstract PersonDAO getPersonDAO();

}
