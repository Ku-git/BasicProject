package com.openhome.thread.download;

import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Download {

    public static void main(String[] args) throws Exception {
        String[] urls = {
                "http://openhome.cc/Gossip/Encoding/",
                "http://openhome.cc/Gossip/Scala/",
                "http://openhome.cc/Gossip/JavaScript/",
                "http://openhome.cc/Gossip/Python/",
        };

        String[] filesNames = {
                "Encoding.html",
                "Scala.html",
                "JavaScript.html",
                "Python.html",
        };
        var start = System.currentTimeMillis();
        for (int i = 0; i < urls.length; i++) {
            dump(openStream(urls[i]), new FileOutputStream(filesNames[i]));
        }
        System.out.println("cost: " + (System.currentTimeMillis() - start));
    }

    static InputStream openStream(String uri) throws Exception {
        InputStream result = HttpClient.newHttpClient()
                .send(HttpRequest.newBuilder(URI.create(uri)).build(),
                        HttpResponse.BodyHandlers.ofInputStream())
                .body();
        return result;
    }

    static void dump(InputStream src, OutputStream dest) throws IOException {

        try(src; dest) {
            byte[] data = new byte[1024];
            int length = 0;
            while ((length = src.read(data)) != -1) {
                dest.write(data, 0, length);
            }
        }
    }
}
