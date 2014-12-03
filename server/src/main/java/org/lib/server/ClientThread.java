/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamClass;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lib.business.LibraryFacade;
import org.lib.protocol.AbstractCommand;
import org.lib.protocol.Logout;
import org.lib.protocol.ProtocolActivator;
import org.lib.utils.LibraryException;

/**
 *
 * @author danecek
 */
public class ClientThread extends Thread {

    static class MyObjectInputStream extends ObjectInputStream {

        @Override
        public Class resolveClass(ObjectStreamClass desc) throws IOException,
                ClassNotFoundException {
            return ProtocolActivator.getContext().getBundle().loadClass(desc.getName());
        }

        public MyObjectInputStream(InputStream in) throws IOException {
            super(in);
        }

    }

    Socket s;
    MyObjectInputStream inp;
    ObjectOutputStream out;

    static final Logger logger = Logger.getLogger(ClientThread.class.getName());

    public ClientThread(Socket s) {
        super(s.getInetAddress() + ":" + s.getPort());
        try {
            this.s = s;
            out = new ObjectOutputStream(s.getOutputStream());
            inp = new MyObjectInputStream(s.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        logger.log(Level.INFO, "New client: {0}", getName());
        try (ObjectInputStream linp = inp;
                ObjectOutputStream lout = out;
                Socket ls = s) {
            for (;;) {
                try {
                    AbstractCommand com = (AbstractCommand) linp.readObject();
                    logger.log(Level.INFO, "{0}: {1}", new Object[]{getName(), com});
                    if (com instanceof Logout) {
                        logger.info("Client loggedout");
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
