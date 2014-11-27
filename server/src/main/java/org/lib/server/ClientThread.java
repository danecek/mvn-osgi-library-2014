/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lib.business.LibraryFacade;
import org.lib.protocol.AbstractCommand;
import org.lib.protocol.Logout;
import org.lib.utils.LibraryException;

/**
 *
 * @author danecek
 */
public class ClientThread implements Runnable {

    Socket s;
    ObjectInputStream inp;
    ObjectOutputStream out;
    static Logger logger = Logger.getAnonymousLogger();

    public ClientThread(Socket s) {
        try {
            this.s = s;
            inp = new ObjectInputStream(s.getInputStream());
            out = new ObjectOutputStream(s.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        logger.info("new client");
        try (
                ObjectInputStream linp = inp;
                ObjectOutputStream lout = out;
                Socket ls = s) {
            for (;;) {
                try {
                    AbstractCommand com = (AbstractCommand) linp.readObject();
                    if (com instanceof Logout) {
                        logger.info("client loggedout");
                        break;
                    }
                    Object result;
                    try {
                        result = com.execute(LibraryFacade.getInstance());
                    } catch (LibraryException ex) {
                        result = ex;
                    }
                    lout.writeObject(result);
                    lout.flush();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                    System.exit(1);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
