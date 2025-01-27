package com.openhome.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NonBlockingClient {

    public static void main(String[] args) throws IOException {
        InetSocketAddress address = new InetSocketAddress("localhost", 8080);
        SocketChannel clientChannel = SocketChannel.open(address);
        clientChannel.configureBlocking(false);

        // 發送數據到服務器
        ByteBuffer buffer = ByteBuffer.allocate(256);
        buffer.put("Hello Server".getBytes());
        buffer.flip();
        clientChannel.write(buffer);

        // 接收服務器的回應
        buffer.clear();
        int bytesRead = clientChannel.read(buffer);
        while (bytesRead == 0) {
            bytesRead = clientChannel.read(buffer);
        }
        buffer.flip();
        while (buffer.hasRemaining()) {
            System.out.print((char) buffer.get());
        }

        clientChannel.close();
    }
}

