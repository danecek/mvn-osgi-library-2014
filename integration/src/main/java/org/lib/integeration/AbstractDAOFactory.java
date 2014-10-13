/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.integeration;

import org.lib.integeration.impl.DefaultDAOFactory;

/**
 *
 * @author danecek
 */
public abstract class AbstractDAOFactory {
    
    private static AbstractDAOFactory instance;
    
    public static AbstractDAOFactory getInstance() {
        if (instance == null) {
           // TODP vyhleda sluzbu
            instance = new DefaultDAOFactory();
        }
        return instance;
    }
    
    
    public abstract BookDAO getBooksDAO();
    
}