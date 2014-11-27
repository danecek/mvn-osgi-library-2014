/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author danecek
 */
public class ServerThread implements Runnable {

    static final Logger logger = Logger.getLogger(ServerThread.class.getName());

    ServerSocket ss;
    int port;

    public ServerThread(int port) {
        this.port = port;
        try {
            ss = new ServerSocket(port);
        } catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        logger.info("server is waiting on port: " + port);
        try {
            for (;;) {
                logger.info("waiting for client");
                Socket s = ss.accept();
                new Thread(new ClientThread(s)).start();
            }
        } catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }

}
