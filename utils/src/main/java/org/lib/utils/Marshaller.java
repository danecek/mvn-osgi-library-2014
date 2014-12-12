/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author danecek
 */
public class Marshaller {
    
    private static final Logger LOG = Logger.getLogger(Marshaller.class.getName());
    
    public static Object bytes2Object(byte[] bytes) {
        try {
            return new ObjectInputStream(new ByteArrayInputStream(bytes)).readObject();
        } catch (IOException | ClassNotFoundException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }
    
//    public static Object readObject(DataInputStream dis) throws IOException {
//        int len = dis.readShort();
//        LOG.info("resp len:" + len);
//        byte[] result = new byte[len];
//        LOG.info("mess lenresp: " + len);
//        dis.read(result);
//        LOG.info(Arrays.toString(result));
//        return bytes2Object(result);
//    }
    
    public static byte[] obj2Bytes(Object obj) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);) {
            oos.writeObject(obj);
            oos.flush();
            return baos.toByteArray();
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }
    
}
