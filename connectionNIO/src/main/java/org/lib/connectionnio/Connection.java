/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.connectionnio;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXB;
import org.lib.protocol.AbstractCommand;
import org.lib.protocol.Logout;
import org.lib.richclient.controller.ActionState;
import org.lib.utils.LibraryException;

/**
 *
 * @author danecek
 */
public class Connection {


    
    ObjectInputStream ois;
    DataOutputStream oos;
    private Socket socket;

    public static Connection instance = new Connection();

    public void connect(String host, int port) throws IOException {
        if (isConnected()) {
            return;
        }
        socket = new Socket(host, port);
        socket.setSoTimeout(2000);
        ois = new ObjectInputStream(socket.getInputStream());
        oos = new DataOutputStream(socket.getOutputStream());
        ActionState.instance.fire();
    }

    private Connection() {

    }

    public <T> T send(AbstractCommand comm) throws LibraryException {

        if (socket == null) {
            throw new LibraryException("Not connected");
        }
        try {
//            JAXB.marshal(comm, oos);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream toos = new ObjectOutputStream(baos);
            toos.writeObject(comm);
            byte[] commBytes = baos.toByteArray();
            oos.writeShort(commBytes.length);
            oos.write(commBytes);
            oos.flush();
            Object result = ois.readObject();
            if (result instanceof LibraryException) {
                throw (LibraryException) result;
            }
            return (T) result;
        } catch (IOException ex) {
            throw new LibraryException(ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Connection.class
                    .getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }

    public boolean isConnected() {
        return socket != null;
    }

    public void disconnect() {
        if (!isConnected()) {
            return;
        }
        try {
            send(new Logout());
        } catch (LibraryException ex) {
            Logger.getLogger(Connection.class
                    .getName()).info(ex.toString());
        }
        try (Socket socket = this.socket;
                ObjectInputStream ois = this.ois;
                DataOutputStream oos = this.oos) {

        } catch (IOException ex) {
        }
        socket = null;
        ActionState.instance.fire();

    }

}
