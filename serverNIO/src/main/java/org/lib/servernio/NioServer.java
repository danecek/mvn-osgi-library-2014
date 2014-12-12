package org.lib.servernio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

class NioServer implements Runnable {

    private final Selector socketSelector;
    static final int COMMAND_BUFF_LEN = 2048;
    static final Logger logger = Logger.getLogger(NioServer.class.getName());
    private final ExecutorService threadPool = Executors.newCachedThreadPool();

    public NioServer(int port) throws IOException {
        socketSelector = SelectorProvider.provider().openSelector();
        ServerSocketChannel serverChannel
                = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);
        serverChannel.socket().bind(new InetSocketAddress(port));
        serverChannel.register(socketSelector,
                SelectionKey.OP_ACCEPT);
    }

    @Override
    public void run() {
        while (true) {
            try {
                logger.info("waiting for client or command");
                int n = socketSelector.select();
                logger.info(n + " selected keys");
                Iterator<SelectionKey> selectedKeys
                        = socketSelector.selectedKeys().iterator();
                while (selectedKeys.hasNext()) {
                    SelectionKey key = selectedKeys.next();
                    selectedKeys.remove();
                    if (!key.isValid()) {
                        continue;
                    }
                    if (key.isAcceptable()) {
                        accept(key);
                    } else if (key.isReadable()) {
                        read(key);
                    }
                }
            } catch (Exception e) {
                logger.log(Level.SEVERE, null, e);
            }
        }

    }

    private void accept(SelectionKey key) throws IOException {
        ServerSocketChannel serverSocketChannel
                = (ServerSocketChannel) key.channel();
        SocketChannel socketChannel = serverSocketChannel.accept();
        socketChannel.configureBlocking(false);
        socketChannel.register(socketSelector,
                SelectionKey.OP_READ);
        logger.info("accepted channel: " + socketChannel);
    }

    private void read(SelectionKey key) throws IOException {
        logger.info("read: " + key);
        SocketChannel socketChannel
                = (SocketChannel) key.channel();
        logger.info("read channel: " + socketChannel);
        ByteBuffer readBuffer = (ByteBuffer) (key.attachment());
        if (readBuffer == null) {
            readBuffer = ByteBuffer.allocate(COMMAND_BUFF_LEN);
            key.attach(readBuffer);
        }

        int numRead;
        try {
            numRead = socketChannel.read(readBuffer);
            //      logger.info("read Buffer: " + readBuffer.toString());
        } catch (IOException e) {
            logger.info(e.toString());
// The remote forcibly closed the connection, 
            socketChannel.close();
            key.cancel();
            return;
        }
        if (numRead == -1) // Remote entity shut the socket down cleanly.
        {
            socketChannel.close();
            key.cancel();
            return;
        }
        int commandLength = readBuffer.getShort(0);
        if (commandLength >= COMMAND_BUFF_LEN - 2) {
            throw new RuntimeException("commandLength >= COMMAND_BUFF_LEN - 2");
        }
        //       logger.info("command length: " + reqLength);
        if (commandLength > readBuffer.position()) {
            return; // zpráva není celá
        }
        readBuffer.flip();
        byte[] req
                = Arrays.copyOfRange(readBuffer.array(), 2, 2 + commandLength);
        readBuffer.position(2 + commandLength);
        readBuffer.compact();
        //       logger.info("compacted Buffer: " + readBuffer.toString());
        // threadPool.execute(new ClientTask(req, socketChannel));
        new ClientTask(req, socketChannel).run();
    }

}
