/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.connection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lib.protocol.AbstractCommand;
import org.lib.utils.LibraryException;

/**
 *
 * @author danecek
 */
public class Connection {

    ObjectInputStream ois;
    ObjectOutputStream oos;
    private Socket socket;

    public static Connection instance = new Connection();

    public void connect(String host, int port) throws IOException {
        socket = new Socket(host, port);
        ois = new ObjectInputStream(socket.getInputStream());
        oos = new ObjectOutputStream(socket.getOutputStream());
        socket.setSoTimeout(3000);
    }

    private Connection() {
    }

    public <T> T send(AbstractCommand comm) throws LibraryException {
        if (socket == null) {
            throw new LibraryException("not connected");
        }
        try {
            oos.writeObject(comm);
            oos.flush();
            Object result = ois.readObject();
            if (result instanceof LibraryException) {
                throw (LibraryException) result;
            }
            return (T) result;
        } catch (IOException ex) {
            throw new LibraryException(ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }

    public void disconnect() {
        try (
                ObjectInputStream ois = this.ois;
                ObjectOutputStream oos = this.oos;
                Socket socket = this.socket) {

        } catch (IOException ex) {
        }
        socket = null;

    }

}
