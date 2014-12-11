/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.servernio;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lib.business.LibraryFacade;
import org.lib.protocol.AbstractCommand;
import org.lib.utils.LibraryException;

/**
 *
 * @author danecek
 */
public class ClientTask implements Runnable {

    byte[] commBytes;
    SocketChannel socketChannel;

    public ClientTask(byte[] commBytes, SocketChannel socketChannel) {
        this.commBytes = commBytes;
        this.socketChannel = socketChannel;
    }

    @Override
    public void run() {
        ObjectInputStream ois = null;
        ByteBuffer writeBuff = null;
        ByteArrayOutputStream baos = null;
        Object result = null;
        ObjectOutputStream oos = null;
        try {
            ois = new ObjectInputStream(new ByteArrayInputStream(commBytes));
            AbstractCommand comm = (AbstractCommand) ois.readObject();
            result = comm.execute(LibraryFacade.getInstance());
            writeBuff = ByteBuffer.allocate(10000);
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ClientTask.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LibraryException ex) {
            result = ex;
        } finally {
            try {
                ois.close();
            } catch (IOException ex) {
                Logger.getLogger(ClientTask.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            oos.writeObject(result);
            writeBuff.put(baos.toByteArray());
            writeBuff.compact();
            socketChannel.write(writeBuff);
        } catch (IOException ex) {
            Logger.getLogger(ClientTask.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
