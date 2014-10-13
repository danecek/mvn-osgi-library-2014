/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.integeration.impl;

import org.lib.integeration.AbstractDAOFactory;
import org.lib.integeration.BookDAO;

/**
 *
 * @author danecek
 */
public class DefaultDAOFactory extends AbstractDAOFactory {

    @Override
    public BookDAO getBooksDAO() {
        return new DefaultBookDAO();
    }
    
}
