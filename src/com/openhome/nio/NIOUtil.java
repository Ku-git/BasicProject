package com.openhome.nio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

public class NIOUtil {

    public static void main(String[] args) throws IOException, InterruptedException {
        ReadableByteChannel src = Channels.newChannel(openStream("https://openhome.cc"));
        try(FileOutputStream fos = new FileOutputStream("index.html")) {
            NIOUtil.dump(src, fos.getChannel());
        }
    }

    static InputStream openStream(String uri) throws IOException, InterruptedException {

        return HttpClient.newHttpClient()
                .send(HttpRequest
                        .newBuilder(URI.create(uri))
                        .build(),
                        HttpResponse.BodyHandlers.ofInputStream())
                .body();
    }

    static void dump(ReadableByteChannel src, WritableByteChannel dest) throws IOException {

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        try(src; dest) {
            while(src.read(buffer) != -1) {
                buffer.flip();
                dest.write(buffer);
                buffer.clear();
            }
        }
    }
}
