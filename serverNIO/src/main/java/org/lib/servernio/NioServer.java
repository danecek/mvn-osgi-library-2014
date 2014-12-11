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
import java.util.logging.Logger;

class NioServer implements Runnable {

    private final Selector socketSelector;
    static final int BUFF_LEN = 2048;
    Logger logger = Logger.getLogger(NioServer.class.getName());

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
            }
        }

    }

    private void accept(SelectionKey key) throws IOException {
        logger.info("client connected: " + key);
        ServerSocketChannel serverSocketChannel
                = (ServerSocketChannel) key.channel();
        SocketChannel socketChannel = serverSocketChannel.accept();
        socketChannel.configureBlocking(false);
        socketChannel.register(socketSelector,
                SelectionKey.OP_READ);
        key.attach(ByteBuffer.allocate(BUFF_LEN));
    }

    private void read(SelectionKey key) throws IOException {
        SocketChannel socketChannel
                = (SocketChannel) key.channel();
        ByteBuffer readBuffer = (ByteBuffer) (key.attachment());
        int numRead;
        try {
            numRead = socketChannel.read(readBuffer);
        } catch (IOException e) {
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
        processBuffer(socketChannel, readBuffer);
    }

    private void processBuffer(SocketChannel socketChannel,
            ByteBuffer readBuffer) throws IOException {
        int reqLength = readBuffer.getShort(0);
        if (reqLength > readBuffer.position()) {
            return; // zpráva není celá
        }
        logger.info("command length: " + reqLength);
        byte[] req
                = Arrays.copyOfRange(readBuffer.array(), 2, 2 + reqLength);
        new ClientTask(req, socketChannel).run();
    }

}
