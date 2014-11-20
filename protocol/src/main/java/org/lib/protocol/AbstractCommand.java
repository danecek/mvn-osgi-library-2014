/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.protocol;

import org.lib.business.LibraryFacade;
import org.lib.utils.LibraryException;

/**
 *
 * @author danecek
 */
public abstract class AbstractCommand<T> {

    public static String OK = "ok";

    abstract T execute(LibraryFacade f) throws LibraryException;
}
