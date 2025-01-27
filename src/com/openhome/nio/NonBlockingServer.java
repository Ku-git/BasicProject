package com.openhome.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NonBlockingServer {

    public static void main(String[] args) throws IOException {
        // 創建 Selector
        Selector selector = Selector.open();

        // 創建 ServerSocketChannel 並綁定端口
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.bind(new InetSocketAddress(8080));
        serverChannel.configureBlocking(false); // 設置為非阻塞模式

        // 將 ServerSocketChannel 註冊到 Selector，關注 OP_ACCEPT 事件
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);

        System.out.println("Server started on port 8080...");

        while (true) {
            selector.select(); // 阻塞直到有事件發生
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> iter = selectedKeys.iterator();

            while (iter.hasNext()) {
                SelectionKey key = iter.next();
                iter.remove(); // 移除當前的 key

                if (key.isAcceptable()) {
                    // 處理新連接
                    handleAccept(key, selector);
                } else if (key.isReadable()) {
                    // 處理讀事件
                    handleRead(key);
                }
            }
        }
    }

    private static void handleAccept(SelectionKey key, Selector selector) throws IOException {
        ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
        SocketChannel clientChannel = serverChannel.accept();
        clientChannel.configureBlocking(false); // 設置為非阻塞模式
        clientChannel.register(selector, SelectionKey.OP_READ); // 註冊讀事件

        System.out.println("Accepted connection from " + clientChannel.getRemoteAddress());
    }

    private static void handleRead(SelectionKey key) throws IOException {
        SocketChannel clientChannel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(256);

        int bytesRead = clientChannel.read(buffer);
        if (bytesRead == -1) {
            // 客戶端關閉連接
            clientChannel.close();
            return;
        }

        buffer.flip();
        while (buffer.hasRemaining()) {
            System.out.print((char) buffer.get());
        }
        buffer.clear();

        // 回應客戶端
        buffer.put("Message received".getBytes());
        buffer.flip();
        clientChannel.write(buffer);
    }
}

