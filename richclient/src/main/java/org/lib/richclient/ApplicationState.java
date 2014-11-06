/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.richclient;

import java.util.Collection;
import java.util.HashSet;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

/**
 *
 * @author danecek
 */
public class ApplicationState implements Observable {
    
    public static ApplicationState INSTANCE = new ApplicationState();
    
    private ApplicationState() {
    }
    
    Collection<InvalidationListener> listeners = new HashSet<>();
    
    @Override
    public void addListener(InvalidationListener il) {
        listeners.add(il);
    }
    
    @Override
    public void removeListener(InvalidationListener il) {
    }
    
    public void invalidate() {
        for (InvalidationListener il : listeners) {
            il.invalidated(this);
        }
    }
    
}
